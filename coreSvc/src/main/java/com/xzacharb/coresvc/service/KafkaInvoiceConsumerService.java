package com.xzacharb.coresvc.service;

import com.xzacharb.coresvc.configuration.KafkaConsumerConfig;
import com.xzacharb.coresvc.model.dao.*;
import com.xzacharb.coresvc.model.dto.ContractorKafka;
import com.xzacharb.coresvc.model.dto.InvoiceData;
import com.xzacharb.coresvc.model.dto.InvoiceDataKafka;
import com.xzacharb.coresvc.model.dto.ManagementPerson;
import com.xzacharb.coresvc.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class KafkaInvoiceConsumerService {
    @Autowired
    KafkaConsumerConfig kafkaInvoiceConfig;

    @Autowired
    public ManagementPersonRepo managementPersonRepo;

    @Autowired
    public ManagementTypeRepo managementTypeRepo;

    @Autowired
    public InvoicesRepo invoicesRepo;

    @Autowired
    public EvaluatedResultRepo evaluatedResultRepo;

    @Autowired
    public LegalFormRepo legalFormRepo;

    @Autowired
    public RoleRepo roleRepo;

    @Autowired
    public ContractorRepo contractorRepo;

    @Autowired
    public CityRepo cityRepo;

    private final City saveOrCreateCityDao(City city) {
        City cityDao = cityRepo.findById(city.getCity_short()).orElse(null);
        if (cityDao == null) {
            cityRepo.save(city);
            return saveOrCreateCityDao(city);
        } else
            return cityDao;
    }

    private final Role saveOrCreateRoleDao(Role role) {
        Role roleDao = roleRepo.findById(role.getRole_name()).orElse(null);
        if (roleDao == null) {
            roleRepo.save(role);
            return saveOrCreateRoleDao(role);
        } else
            return roleDao;
    }

    private final ManagementType saveOrCreateManagementTypeDao(ManagementType managementType) {
        ManagementType managementTypeDao = managementTypeRepo.findById(managementType.getType_name()).orElse(null);
        if (managementTypeDao == null) {
            managementTypeRepo.save(managementType);
            return saveOrCreateManagementTypeDao(managementType);
        } else
            return managementTypeDao;
    }

    private final ManagementPersonDao saveOrCreatePersonDao(Contractor ct, ManagementPerson person, City cityDao) {
        ManagementPersonDao managementPersonDao = managementPersonRepo.findBySourceAndNameAndAddress(person.getSource(), person.getName(), person.getAddress()).orElse(null);
        if (managementPersonDao == null) {
            Role role = saveOrCreateRoleDao(new Role(person.getRole()));
            ManagementType managementType = saveOrCreateManagementTypeDao(new ManagementType(person.getManagementType()));
            ManagementPersonDao personDao = new ManagementPersonDao(person.getName(), person.getMiddle_name(), person.getSure_name(), person.getAddress(), person.getSource(), person.getDate_start(), role, ct, cityDao, managementType);

            managementPersonRepo.save(personDao);
            return saveOrCreatePersonDao(ct, person, cityDao);
        } else
            return managementPersonDao;
    }

    private final Contractor saveOrCreateContractorDao(ContractorKafka contractorKafka) {
        Contractor contractorDao = contractorRepo.findByIco(contractorKafka.getIco()).orElse(null);
        if (contractorDao == null) {
            LegalForm legalFormDao = saveOrCreateLegalFormDao(contractorKafka.getLegalFormId());
            Contractor ct = new Contractor(contractorKafka.getName(), contractorKafka.getAddress(), contractorKafka.getDescription(), contractorKafka.getSource(), contractorKafka.getIco(), contractorKafka.getDate_created(), legalFormDao);
            contractorRepo.save(ct);
            return saveOrCreateContractorDao(contractorKafka);
        } else
            return contractorDao;
    }

    private final LegalForm saveOrCreateLegalFormDao(String legalFormName) {
        String legalFormShort = legalFormName.replaceAll("\\s+", "");
        LegalForm legalFormDao = legalFormRepo.findById(legalFormShort).orElse(null);
        if (legalFormDao == null) {
            legalFormRepo.save(new LegalForm(legalFormShort, legalFormName));
            return saveOrCreateLegalFormDao(legalFormName);
        } else
            return legalFormDao;
    }

    @KafkaListener(topics = "${spring.kafka.topic.first}", groupId = "default", containerFactory = "kafkaInvoiceListenerContainerFactory")
    public void listenGroupFoo(InvoiceDataKafka invoiceData) {

        City cityDao = saveOrCreateCityDao(invoiceData.getCity());
        InvoiceDao invoiceDao = invoicesRepo.findByCityAndDescription(cityDao, invoiceData.getDescription()).orElse(null);
        if (invoiceDao == null) {
            Contractor contractor = saveOrCreateContractorDao(invoiceData.getContractor());
            for (ManagementPerson person : invoiceData.getPersonList()) {
                saveOrCreatePersonDao(contractor, person, cityDao);
            }
            invoiceDao = new InvoiceDao(invoiceData.getPrice(), invoiceData.getSubject(), invoiceData.getDescription(), invoiceData.getComment(), invoiceData.getDate_signed(), invoiceData.getDate_published(), invoiceData.getSource(), cityDao, contractor);
            invoicesRepo.save(invoiceDao);
        }

        System.out.println("we have successful ordered and going to send success message: " + invoiceData);
    }
}
