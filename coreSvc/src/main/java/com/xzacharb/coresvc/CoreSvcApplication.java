package com.xzacharb.coresvc;

import com.xzacharb.coresvc.model.dao.*;
import com.xzacharb.coresvc.model.service.CoreDbService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class CoreSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreSvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner sampleData(CoreDbService repository) {
        return (args) -> {
            LegalFormDao lf = new LegalFormDao("lfs1", "Legal form sample 1");
            ContractorDao contractorDao = new ContractorDao("sample name", "sample address", "sample description", "sample source", "sample ico", new Date(), lf);
            CityDao city = new CityDao("sampleCity");
            RoleDao role = new RoleDao("sampleName1");
            ManagementTypeDao managementTypeDao = new ManagementTypeDao("sampleType1");
            ManagementPersonDao companyPersonDao1 = new ManagementPersonDao("Person name1", "Person middleName1", "person sureName1", "Person address1", "Person source1", new Date(), role, contractorDao, city, managementTypeDao);
            ManagementPersonDao companyPersonDao2 = new ManagementPersonDao("Person name2", "Person middleName2", "person sureName2", "Person address2", "Person source2", new Date(), role, contractorDao, city, managementTypeDao);
            InvoiceDao invoiceDao = new InvoiceDao(1558850, "invoice sample subject", "invoice sample description", "invoice sample comment", null, new Date(), "invoice sample source", city, contractorDao);
            EvaluatedResultDao evaluatedResultDao= new EvaluatedResultDao("evaluated sample suspicious value", "evaluated sample suspicious description",  "evaluated sample suspicious evaluator_name", invoiceDao);

            repository.roleRepo.save(role);
            repository.legalFormRepo.save(lf);
            repository.cityRepo.save(city);
            repository.contractorRepo.save(contractorDao);
            repository.managementTypeRepo.save(managementTypeDao);
            repository.managementPersonRepo.save(companyPersonDao1);
            repository.managementPersonRepo.save(companyPersonDao2);
            repository.invoicesRepo.save(invoiceDao);
            repository.evaluatedResultRepo.save(evaluatedResultDao);
        };
    }
}
