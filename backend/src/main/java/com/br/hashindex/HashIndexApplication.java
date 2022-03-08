package com.br.hashindex;

import com.br.hashindex.model.Configuration;
import com.br.hashindex.model.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HashIndexApplication {

	public static void main(String[] args) {
		Database database = new Database();

		new Configuration.Builder()
				.setPageSize(1)
				.setBucketSize(2)
				.setColisionCount(0)
				.setOverflowCount(0)
				.setDatabase(database)
				.build();

		SpringApplication.run(HashIndexApplication.class, args);
	}

}
