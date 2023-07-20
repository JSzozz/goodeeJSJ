package com.bs.boot.testboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TestbootApplication implements CommandLineRunner{
	
	@Autowired
	private ApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(TestbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String[] beans=context.getBeanDefinitionNames();
		Arrays.stream(beans).forEach(System.out::println);
	}
	
	
}
