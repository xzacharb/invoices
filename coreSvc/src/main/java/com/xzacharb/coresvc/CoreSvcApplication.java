package com.xzacharb.coresvc;

import com.xzacharb.coresvc.model.dao.*;
import com.xzacharb.coresvc.model.dao.Process;
import com.xzacharb.coresvc.model.service.CoreDbService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;

import java.util.Date;

@SpringBootApplication
@EnableKafka
public class CoreSvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreSvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner sampleData(CoreDbService repository) {
        return (args) -> {
            LegalForm lf1 = new LegalForm("lfs1", "Legal form sample 1");
            LegalForm lf2 = new LegalForm("lfs1", "Legal form sample 2");
            Contractor contractor1 = new Contractor("sample name1", "sample address", "sample description", "sample source", "sample ico1", new Date(), lf1);
            Contractor contractor2 = new Contractor("sample name2", "sample address", "sample description", "sample source", "sample ico2", new Date(), lf2);
            Contractor contractor3 = new Contractor("sample name3", "sample address", "sample description", "sample source", "sample ico3", new Date(), lf1);
            Contractor contractor4 = new Contractor("sample name4", "sample address", "sample description", "sample source", "", new Date(), lf2);
            City city1 = new City("sampleCity1");
            City city2 = new City("sampleCity2");
            City city3 = new City("sampleCity3");
            City city4 = new City("sampleCity4");
            Role role1 = new Role("sampleName1");
            Role role2 = new Role("sampleName2");
            Role role3 = new Role("sampleName3");
            ManagementType managementType1 = new ManagementType("sampleType1");
            ManagementType managementType2 = new ManagementType("sampleType2");
            ManagementPersonDao companyPersonDao1 = new ManagementPersonDao("Person name1", "Person middleName1", "person sureName1", "Person address1", "Person source1", new Date(), role1, contractor1, city1, managementType1);
            ManagementPersonDao companyPersonDao2 = new ManagementPersonDao("Person name2", "Person middleName2", "person sureName2", "Person address2", "Person source2", new Date(), role2, contractor1, city2, managementType1);
            ManagementPersonDao companyPersonDao3 = new ManagementPersonDao("Person name3", "", "person sureName3", "Person address2", "Person source3", new Date(), role3, contractor1, city2, managementType2);
            ManagementPersonDao companyPersonDao4 = new ManagementPersonDao("Person name4", "Person middleName3", "person sureName3", "Person address3", "Person source3", new Date(), role2, contractor3, city1, managementType2);
            ManagementPersonDao companyPersonDao5 = new ManagementPersonDao("Person name5", "", "person sureName4", "Person address31", "Person source31", new Date(), role2, contractor2, city1, managementType2);
            ManagementPersonDao companyPersonDao6 = new ManagementPersonDao("Person name6", "", "person sureName33", "Person address32", "Person source23", new Date(), role2, contractor3, city1, managementType2);
            InvoiceDao invoiceDao1 = new InvoiceDao(1558850, "invoice sample subject", "invoice sample description", "invoice sample comment", null, new Date(), "invoice sample source", city1, contractor1);
            InvoiceDao invoiceDao2 = new InvoiceDao(1558850, "invoice sample subject", "invoice sample description", "invoice sample comment", new Date(), new Date(), "invoice sample source", city1, contractor2);
            InvoiceDao invoiceDao3 = new InvoiceDao(1558850, "invoice sample subject", "invoice sample description", "invoice sample comment", null, new Date(), "invoice sample source", city2, contractor3);
            InvoiceDao invoiceDao4 = new InvoiceDao(1558850, "invoice sample subject", "invoice sample description", "invoice sample comment", new Date(), new Date(), "invoice sample source", city2, contractor4);
            InvoiceDao invoiceDao5 = new InvoiceDao(1558850, "invoice sample subject", "invoice sample description", "invoice sample comment", null, null, "invoice sample source", city3, contractor2);
            InvoiceDao invoiceDao6 = new InvoiceDao(1558850, "invoice sample subject", "invoice sample description", "invoice sample comment", new Date(), null, "invoice sample source", city3, contractor2);
            InvoiceDao invoiceDao7 = new InvoiceDao(1558850, "invoice sample subject", "invoice sample description", "invoice sample comment", null, new Date(), "invoice sample source", city2, contractor3);
            InvoiceDao invoiceDao8 = new InvoiceDao(1558850, "invoice sample subject", "invoice sample description", "invoice sample comment", new Date(), new Date(), "invoice sample source", city1, contractor1);
            InvoiceDao invoiceDao9 = new InvoiceDao(1558850, "invoice sample subject", "invoice sample description", "invoice sample comment", null, null, "invoice sample source", city1, contractor2);
            InvoiceDao invoiceDao10 = new InvoiceDao(1558850, "invoice sample subject", "invoice sample description", "invoice sample comment", new Date(), null, "invoice sample source", city3, contractor1);
            InvoiceDao invoiceDao11 = new InvoiceDao(1558850, "invoice sample subject", "invoice sample description", "invoice sample comment", new Date(), null, "invoice sample source", city2, contractor1);
            EvaluatedResult evaluatedResult1 = new EvaluatedResult("evaluated sample suspicious value1", "evaluated sample suspicious description",  "evaluated sample suspicious evaluator_name1", invoiceDao1);
            EvaluatedResult evaluatedResult2 = new EvaluatedResult("evaluated sample suspicious value2", "evaluated sample suspicious description",  "evaluated sample suspicious evaluator_name2", invoiceDao1);
            EvaluatedResult evaluatedResult3 = new EvaluatedResult("evaluated sample suspicious value3", "evaluated sample suspicious description",  "evaluated sample suspicious evaluator_name", invoiceDao2);
            EvaluatedResult evaluatedResult4 = new EvaluatedResult("evaluated sample suspicious value4", "evaluated sample suspicious description",  "evaluated sample suspicious evaluator_name1", invoiceDao4);
            EvaluatedResult evaluatedResult5 = new EvaluatedResult("evaluated sample suspicious value5", "evaluated sample suspicious description",  "evaluated sample suspicious evaluator_name2", invoiceDao4);
            EvaluatedResult evaluatedResult6 = new EvaluatedResult("evaluated sample suspicious value6", "evaluated sample suspicious description",  "evaluated sample suspicious evaluator_name3", invoiceDao5);
            EvaluatedResult evaluatedResult7 = new EvaluatedResult("evaluated sample suspicious value7", "evaluated sample suspicious description",  "evaluated sample suspicious evaluator_name2", invoiceDao11);
            Process process1 = new Process(1l, "Proces1 name", "Proces1 descrition", "status1", new Date(), new Date());
            Process process2 = new Process(1l, "Proces2 name", "Proces2 descrition", "status2", new Date(), new Date());
            Process process3 = new Process(1l, "Proces3 name", "Proces3 descrition", "status2", new Date(), new Date());
            Process process4 = new Process(1l, "Proces4 name", "Proces4 descrition", "status3", new Date(), new Date());
            repository.processesRepo.save(process1);
            repository.processesRepo.save(process2);
            repository.processesRepo.save(process3);
            repository.processesRepo.save(process4);
            repository.roleRepo.save(role1);
            repository.roleRepo.save(role2);
            repository.roleRepo.save(role3);
            repository.legalFormRepo.save(lf1);
            repository.legalFormRepo.save(lf2);
            repository.cityRepo.save(city1);
            repository.cityRepo.save(city2);
            repository.cityRepo.save(city3);
            repository.cityRepo.save(city4);
            repository.contractorRepo.save(contractor1);
            repository.contractorRepo.save(contractor2);
            repository.contractorRepo.save(contractor3);
            repository.contractorRepo.save(contractor4);
            repository.managementTypeRepo.save(managementType1);
            repository.managementTypeRepo.save(managementType2);
            repository.managementPersonRepo.save(companyPersonDao1);
            repository.managementPersonRepo.save(companyPersonDao2);
            repository.managementPersonRepo.save(companyPersonDao3);
            repository.managementPersonRepo.save(companyPersonDao4);
            repository.managementPersonRepo.save(companyPersonDao5);
            repository.managementPersonRepo.save(companyPersonDao6);
            repository.invoicesRepo.save(invoiceDao1);
            repository.invoicesRepo.save(invoiceDao2);
            repository.invoicesRepo.save(invoiceDao3);
            repository.invoicesRepo.save(invoiceDao4);
            repository.invoicesRepo.save(invoiceDao5);
            repository.invoicesRepo.save(invoiceDao6);
            repository.invoicesRepo.save(invoiceDao7);
            repository.invoicesRepo.save(invoiceDao8);
            repository.invoicesRepo.save(invoiceDao9);
            repository.invoicesRepo.save(invoiceDao10);
            repository.invoicesRepo.save(invoiceDao11);
            repository.evaluatedResultRepo.save(evaluatedResult1);
            repository.evaluatedResultRepo.save(evaluatedResult2);
            repository.evaluatedResultRepo.save(evaluatedResult3);
            repository.evaluatedResultRepo.save(evaluatedResult4);
            repository.evaluatedResultRepo.save(evaluatedResult5);
            repository.evaluatedResultRepo.save(evaluatedResult6);
            repository.evaluatedResultRepo.save(evaluatedResult7);
        };
    }
}
