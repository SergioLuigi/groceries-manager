package br.com.sergioluigi.groceriesmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class GroceriesManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceriesManagerApplication.class, args);
	}

}
