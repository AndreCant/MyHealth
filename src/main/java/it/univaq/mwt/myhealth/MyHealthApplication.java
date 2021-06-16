package it.univaq.mwt.myhealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan
@SpringBootApplication
public class MyHealthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyHealthApplication.class, args);
	}

}
