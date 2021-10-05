package com.xzacharb.authsvc;

import com.xzacharb.authsvc.common.AuthorizationLevel;
import com.xzacharb.authsvc.repository.AuthRepository;
import com.xzacharb.authsvc.repository.UserDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AuthSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthSvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner sampleData(AuthRepository repository) {
		return (args) -> {
			repository.save(new UserDao("root","root", AuthorizationLevel.ROOT.byteValueOfLevel()));
			repository.save(new UserDao("admin","admin", AuthorizationLevel.ADMIN.byteValueOfLevel()));
			repository.save(new UserDao("user1","user1", AuthorizationLevel.USER.byteValueOfLevel()));
		};
	}
}
