package com.xzacharb.coresvc.impl.service.microservices;

import com.xzacharb.coresvc.impl.common.KafkaConsumerConfig;
import com.xzacharb.coresvc.impl.component.RepositoryFacade;
import com.xzacharb.coresvc.impl.model.dao.*;
import com.xzacharb.coresvc.impl.model.dto.ContractorKafka;
import com.xzacharb.coresvc.impl.model.dto.InvoiceDataKafka;
import com.xzacharb.coresvc.impl.model.dto.ManagementPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaInvoiceConsumerService {
    @Autowired
    private KafkaConsumerConfig kafkaInvoiceConfig;

    @Autowired
    private RepositoryFacade repository;


    private City saveOrCreateCityDao(City city) {
        City cityDao = repository.cityRepo.findById(city.getCity_short()).orElse(null);
        if (cityDao == null) {
            repository.cityRepo.save(city);
            return saveOrCreateCityDao(city);
        } else
            return cityDao;
    }

    private Role saveOrCreateRoleDao(Role role) {
        Role roleDao = repository.roleRepo.findById(role.getRole_name()).orElse(null);
        if (roleDao == null) {
            repository.roleRepo.save(role);
            return saveOrCreateRoleDao(role);
        } else
            return roleDao;
    }

    private ManagementType saveOrCreateManagementTypeDao(ManagementType managementType) {
        ManagementType managementTypeDao = repository.managementTypeRepo.findById(managementType.getType_name()).orElse(null);
        if (managementTypeDao == null) {
            repository.managementTypeRepo.save(managementType);
            return saveOrCreateManagementTypeDao(managementType);
        } else
            return managementTypeDao;
    }

    private ManagementPersonDao saveOrCreatePersonDao(Contractor ct, ManagementPerson person, City cityDao) {
        ManagementPersonDao managementPersonDao = repository.managementPersonRepo.findBySourceAndNameAndAddress(person.getSource(), person.getName(), person.getAddress()).orElse(null);
        if (managementPersonDao == null) {
            Role role = saveOrCreateRoleDao(new Role(person.getRole()));
            ManagementType managementType = saveOrCreateManagementTypeDao(new ManagementType(person.getManagementType()));
            ManagementPersonDao personDao = new ManagementPersonDao(person.getName(), person.getMiddle_name(), person.getSure_name(), person.getAddress(), person.getSource(), person.getDate_start(), role, ct, cityDao, managementType);

            repository.managementPersonRepo.save(personDao);
            return saveOrCreatePersonDao(ct, person, cityDao);
        } else
            return managementPersonDao;
    }

    private Contractor saveOrCreateContractorDao(ContractorKafka contractorKafka) {
        Contractor contractorDao = repository.contractorRepo.findByIco(contractorKafka.getIco()).orElse(null);
        if (contractorDao == null) {
            LegalForm legalFormDao = saveOrCreateLegalFormDao(contractorKafka.getLegalFormId());
            Contractor ct = new Contractor(contractorKafka.getName(), contractorKafka.getAddress(), contractorKafka.getDescription(), contractorKafka.getSource(), contractorKafka.getIco(), contractorKafka.getDate_created(), legalFormDao);
            repository.contractorRepo.save(ct);
            return saveOrCreateContractorDao(contractorKafka);
        } else
            return contractorDao;
    }

    private LegalForm saveOrCreateLegalFormDao(String legalFormName) {
        String legalFormShort = legalFormName.replaceAll("\\s+", "");
        LegalForm legalFormDao = repository.legalFormRepo.findById(legalFormShort).orElse(null);
        if (legalFormDao == null) {
            repository.legalFormRepo.save(new LegalForm(legalFormShort, legalFormName));
            return saveOrCreateLegalFormDao(legalFormName);
        } else
            return legalFormDao;
    }

    @KafkaListener(topics = "${spring.kafka.topic.first}", groupId = "default", containerFactory = "kafkaInvoiceListenerContainerFactory")
    public void listenGroupFoo(InvoiceDataKafka invoiceData) {
        City cityDao = saveOrCreateCityDao(invoiceData.getCity());
        InvoiceDao invoiceDao = repository.invoicesRepo.findByCityAndDescription(cityDao, invoiceData.getDescription()).orElse(null);
        if (invoiceDao == null) {
            Contractor contractor = saveOrCreateContractorDao(invoiceData.getContractor());
            for (ManagementPerson person : invoiceData.getPersonList()) {
                saveOrCreatePersonDao(contractor, person, cityDao);
            }
            invoiceDao = new InvoiceDao(invoiceData.getPrice(), invoiceData.getSubject(), invoiceData.getDescription(), invoiceData.getComment(), invoiceData.getDate_signed(), invoiceData.getDate_published(), invoiceData.getSource(), cityDao, contractor);
            repository.invoicesRepo.save(invoiceDao);
        }

        System.out.println("we have successful ordered and going to send success message: " + invoiceData);
    }
}
