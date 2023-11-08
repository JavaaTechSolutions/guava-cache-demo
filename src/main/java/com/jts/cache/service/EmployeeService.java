package com.jts.cache.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jts.cache.entity.Employee;
import com.jts.cache.entity.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Optional<Employee> getEmployeeById(String id) {
		return employeeRepository.findById(Long.valueOf(id));
	}
	
	public Employee save(Employee emp) {
		return employeeRepository.save(emp);
	}
}
