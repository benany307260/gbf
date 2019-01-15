package com.bentest.gbf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bentest.gbf.domain.mapper")
public class GbfApplication {

	public static void main(String[] args) {
		SpringApplication.run(GbfApplication.class, args);
	}
}
