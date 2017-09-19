package com.joshwindels.todoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TodooApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodooApplication.class, args);
	}
}
