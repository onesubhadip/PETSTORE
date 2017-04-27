package org.petstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
