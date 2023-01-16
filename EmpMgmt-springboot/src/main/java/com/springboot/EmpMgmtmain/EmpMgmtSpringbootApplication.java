package com.springboot.EmpMgmtmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.springboot")
public class EmpMgmtSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpMgmtSpringbootApplication.class, args);
	}

}
