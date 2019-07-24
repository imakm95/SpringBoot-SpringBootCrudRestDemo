package com.luv2code.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	//private EmployeeDAO employeeDAO;
	private EmployeeRepository employeeRepository;
	
	/*@Autowired
	//public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl") EmployeeDAO theEmployeeDAO) {
	public EmployeeServiceImpl(@Qualifier("employeeDAOHibernateImpl") EmployeeDAO theEmployeeDAO) {
		employeeDAO = theEmployeeDAO;
	}*/
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}	
	
	@Override
	//@Transactional
	public List<Employee> findAll() {
		//return employeeDAO.findAll();
		return employeeRepository.findAll();
	}

	@Override
	//@Transactional
	public Employee findById(Integer theId) {
		//return employeeDAO.findById(theId);
		
		Employee theEmployee = null;
		Optional<Employee> optional = employeeRepository.findById(theId);
		if(optional.isPresent()) {
			theEmployee = optional.get();
		} else {
			theEmployee =null;
		}
		return theEmployee;
	}

	@Override
	//@Transactional
	public void save(Employee theEmployee) {
		//employeeDAO.save(theEmployee);
		 employeeRepository.save(theEmployee);
	}

	@Override
	//@Transactional
	public void deleteById(Integer theId) {
		//employeeDAO.deleteById(theId);
		employeeRepository.deleteById(theId);
	}

}
