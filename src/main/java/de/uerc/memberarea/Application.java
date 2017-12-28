package de.uerc.memberarea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import de.uerc.memberarea.models.users.ClubMember;
import de.uerc.memberarea.security.SpringSecurityAuditorAware;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public StandardPasswordEncoder standardPasswordEncoder() {
		return new StandardPasswordEncoder("Saumäßig geheim!");
	}
	
	@Bean
	public AuditorAware<ClubMember> auditorProvider() {
	    return new SpringSecurityAuditorAware();
	}
}
