package org.petstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application started class. 
 * Application is bootstraped from the main method of this class.
 */
@SpringBootApplication
@EnableJpaRepositories
public class PetStoreApp implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(PetStoreApp.class, args);
	}
	
	@Override
    public void run(String... strings) throws Exception {

	}

}
