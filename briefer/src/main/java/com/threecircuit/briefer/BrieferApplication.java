package com.threecircuit.briefer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.threecircuit.briefer.controller.ServiceController;
import com.threecircuit.briefer.controller.UIController;
import com.threecircuit.briefer.dao.IMemberDAO;
import com.threecircuit.briefer.dao.IPersonalDAO;

@SpringBootApplication
@ComponentScan(basePackageClasses = ServiceController.class)
@ComponentScan(basePackageClasses = UIController.class)

@ComponentScan
@MapperScan(basePackageClasses = IMemberDAO.class)
@ComponentScan
@MapperScan(basePackageClasses = IPersonalDAO.class)

public class BrieferApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrieferApplication.class, args);
	}

}
