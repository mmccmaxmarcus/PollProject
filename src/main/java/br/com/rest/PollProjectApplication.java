package br.com.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.rest.model"})
@ComponentScan(basePackages = {"br.*"})
@EnableJpaRepositories(basePackages = {"br.com.rest.repository"})
@EnableTransactionManagement //Gerencia de transação no BD
@EnableWebMvc //Ativa recurso de MVC
@RestController //Trabalhar com Rest no controller da aplicação
@EnableAutoConfiguration //Spring configura tudo de forma automatica
public class PollProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollProjectApplication.class, args);
	}

}
