package com.spring.basics.data.exporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.basics.ConsoleApplication;
import com.spring.basics.dto.Salary;

public class DataExporter {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	public String convertSalaryToJson(Salary salary) {
		
		try {
			
			ConsoleApplication consoleApplication = applicationContext.getBean(ConsoleApplication.class);
			consoleApplication.sayHello();
			
			SalaryJsonConverter salaryJsonConverter = applicationContext.getBean("sJsonConverter", SalaryJsonConverter.class);
			return salaryJsonConverter.convertToJson(salary);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
}

