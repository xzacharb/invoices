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
			LegalFormDao lf = new LegalFormDao("lfs1","Legal form sample 1");
			ContractorDao contractorDao = new ContractorDao("sample name", "sample address", "sample description",  "sample source", "sample ico", new Date(), lf);
			CityDao city = new CityDao("sampleCity");
			RoleDao role = new RoleDao("sampleName1");
			ManagementTypeDao managementTypeDao = new ManagementTypeDao("sampleType1");
			ManagementPersonDao companyPersonDao = new ManagementPersonDao("Person name",  "Person middleName","person sureName",  "Person address", "Person source", new Date(), role,  contractorDao, city, managementTypeDao);

			repository.roleRepo.save(role);
			repository.legalFormRepo.save(lf);
			repository.cityRepo.save(city);
			repository.contractorRepo.save(contractorDao);
			repository.managementTypeRepo.save(managementTypeDao);
			repository.companyPersonRepo.save(companyPersonDao);
		};
	}
}
