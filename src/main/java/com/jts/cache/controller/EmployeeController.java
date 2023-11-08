package com.jts.cache.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jts.cache.config.CacheStore;
import com.jts.cache.entity.Employee;
import com.jts.cache.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CacheStore<Employee> employeeCache;

	@GetMapping("getById/{id}")
	public Employee getById(@PathVariable String id) {
		System.out.println("Searching Employee by ID  : " + id);

		Employee cacheEmp = employeeCache.get(id);

		if (cacheEmp != null) {
			System.out.println("Employee found in cache with id :: " + id);
			return cacheEmp;
		}

		Optional<Employee> EmpRecordFromBackendService = employeeService.getEmployeeById(id);
		employeeCache.add(id, EmpRecordFromBackendService.get());

		return EmpRecordFromBackendService.get();
	}

	@PostMapping("/save")
	public String save(@RequestBody Employee employee) {
		employeeService.save(employee);
		return "Data saved successfully.";
	}
}
