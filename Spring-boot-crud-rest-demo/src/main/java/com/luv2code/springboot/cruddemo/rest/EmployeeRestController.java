package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.exceptions.EmployeeNotFoundException;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	
	
	
	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		List<Employee> empList = employeeService.findAll();
		if(empList == null || empList.isEmpty()) {
			throw new EmployeeNotFoundException("No employee found in database");
		}
		return empList;
	}
	
	
	

	// add mapping for GET /employees/{employeeId}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable Integer employeeId) {
		
		Employee theEmployee = employeeService.findById(employeeId);
		if(employeeId == null || employeeId<=0 || theEmployee==null) {
			throw new EmployeeNotFoundException("No employee found in database with id - "+employeeId);
		}
		return theEmployee;
	}
	
	
	
	
	
	// add mapping for POST /employees - add new employee
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		// also just in case they pass an id in JSON ... set id to 0 or null
		// this is to force a save of new item ... instead of update
		
		//theEmployee.setId(0);
		theEmployee.setId(null);
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	
	
	
	
	
	// add mapping for PUT /employees - update existing employee
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		if(theEmployee.getId() == null || theEmployee.getId()<=0 || theEmployee==null || employeeService.findById(theEmployee.getId())==null) {
			throw new EmployeeNotFoundException("No employee found in database with id - "+theEmployee.getId());
		}
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	
	
	
	
	// add mapping for DELETE /employees/{employeeId} - delete employee
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable Integer employeeId) {
		
		Employee tempEmployee = employeeService.findById(employeeId);
		
		// throw exception if null
		
		if (employeeId==null || employeeId<=0||tempEmployee == null) {
			throw new EmployeeNotFoundException("No employee found in database with id - " + employeeId);
		}
		
		employeeService.deleteById(employeeId);
		
		return "Employee deleted from database successfully with id - " + employeeId;
	}
	
}










