package com.luv2code.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.cruddemo.entity.Employee;
//Just inject the object of EmployeeRepository in ServiceImpl Class and thats it. now you can levarage all the spring-data-jpa methods for free. This is really an easier solution.
//Now you have to write Dao Interface and Dao Implememtation at all. 
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	//add customized methods if you want low level business logics
	
	//by default we get all the basic crud methods for free, all because of spring-data-jpa      ------>      few methods are findAll(), findById(), save(), DeleteById()
	
	//spring-data-jpa uses optionals to check for null values which is one of the features of JAVA8
	
	//No need to annotate the service class methods (which calls dao methods) with @Transactional as JpaRepository does it out of the box
}
