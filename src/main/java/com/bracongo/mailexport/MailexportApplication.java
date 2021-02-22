package com.bracongo.mailexport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MailexportApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailexportApplication.class, args);
	}

}
