package com.kcom.springstart1.webdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class WebdemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WebdemoApplication.class, args);
	}

}
