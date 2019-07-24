package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	public List<Employee> findAll() {
		// create a query
		System.out.println("GET ALL - " +entityManager.hashCode());
		Query theQuery =
				entityManager.createQuery("from Employee", Employee.class);
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		// return the results		
		return employees;
	}


	@Override
	public Employee findById(Integer theId) {
		
		System.out.println("GET ID - " +entityManager.hashCode());
		// get the employee
		Employee theEmployee =
				entityManager.find(Employee.class, theId);
		
		
		//currentSession.clear();
		
		// return the employee
		return theEmployee;
	}


	@Override
	public void save(Employee theEmployee) {
		
		// save employee
		System.out.println("SAVE/UPDATE - " +entityManager.hashCode());
		Employee tempEmployee = entityManager.merge(theEmployee);
		theEmployee.setId(tempEmployee.getId());
	}


	@Override
	public void deleteById(Integer theId) {
		
		System.out.println("DELETE - " +entityManager.hashCode());
		// delete object with primary key
		Query theQuery =  entityManager.createQuery(
				"delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
	}

}







