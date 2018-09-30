package com.cz.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan
@ComponentScan(basePackages = {"com.cz"})
public class BoottestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoottestApplication.class, args);
	}
}
