package br.com.asoft.usermanagementinterface;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.asoft.usermanagementinterface.model.Usuario;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
@RequestMapping("/")
@EntityScan(basePackageClasses = Usuario.class)
@EnableCaching
public class HealthyCompanySchedulerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HealthyCompanySchedulerApplication.class, args);
	}

	@Value("${database.url}")
	private String databaseUrl;

	@GetMapping(path = "/")
	public String retornaPing() {
		return ping();
	}

	@GetMapping(path = "/ping")
	public String ping() {
		return "ok " + databaseUrl;
	}
}
