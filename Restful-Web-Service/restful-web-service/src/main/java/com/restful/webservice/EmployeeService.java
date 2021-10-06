package com.restful.webservice;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable int id)
	{
		Optional<Employee> emp = employeeRepository.findById(id);
		if(!emp.isPresent())
			throw new EmployeeNotFoundException("Id-"+id);
		else
			return emp;
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteEmployeeById(@PathVariable int id)
	{
		Optional<Employee> emp = employeeRepository.findById(id);
		if(!emp.isPresent())
			throw new EmployeeNotFoundException("Id-"+id);
		else
			employeeRepository.deleteById(id);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee employee)
	{
		Employee emp = employeeRepository.save(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(emp.getId())
		.toUri();

		return ResponseEntity.created(location).build();
	}
}

