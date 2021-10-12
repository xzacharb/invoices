package com.xzacharb.coresvc;

import com.xzacharb.coresvc.model.dao.LegalFormDao;
import com.xzacharb.coresvc.model.dao.RoleDao;
import com.xzacharb.coresvc.model.repository.RoleRepo;
import com.xzacharb.coresvc.model.service.CoreDbService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoreSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreSvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner sampleData(CoreDbService repository) {
		return (args) -> {
			repository.roleRepo.save(new RoleDao("sampleName1"));
			repository.legalFormRepo.save(new LegalFormDao("lfs1","Legal form sample 1"));
		};
	}
}
