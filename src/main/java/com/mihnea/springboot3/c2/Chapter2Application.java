package com.mihnea.springboot3.c2;

import com.mihnea.springboot3.c2.Security.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//@ConfigurationPropertiesScan("com.mihnea.springboot3.c2.Security")
@EnableConfigurationProperties(AppConfig.class)
@SpringBootApplication
public class Chapter2Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter2Application.class, args);
	}

}
