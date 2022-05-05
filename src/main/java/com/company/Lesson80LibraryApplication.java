package com.company;

import com.company.db.DBConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lesson80LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lesson80LibraryApplication.class, args);
		DBConnection.createTable();
	}

}
