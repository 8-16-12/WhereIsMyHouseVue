package com.ssafy.happyhouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ssafy.model.dao"})
@MapperScan(basePackages = {"com.ssafy.model.dao"})
public class WhereIsMyHouseSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereIsMyHouseSpringApplication.class, args);
	}

}
