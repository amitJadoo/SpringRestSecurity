package com.test.example.spring.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.example.spring.model.Employee;
import com.test.example.spring.model.exception.BadRequestException;
import com.test.example.spring.model.exception.ForbiddenException;
import com.test.example.spring.model.exception.ResourceNotFoundException;

@Controller
@RequestMapping("/employee")
public class EmplyeeService {
	static Set<Employee> empSet;
	static {
		empSet = new HashSet<Employee>();
		Employee employee = null;
		for (int i = 0; i < 10; i++) {
			employee = new Employee(i, "Employee " + i);
			empSet.add(employee);
		}
	}

	@RequestMapping(value = "/{empId}", method = RequestMethod.GET, headers = "Accept=application/xml, application/json", produces = {
			"application/json", "application/xml" })
	@ResponseBody
	public Employee getEmployee(@PathVariable int empId)
			throws BadRequestException, ResourceNotFoundException {
		// Validation
		if (empId < 0) {
			throw new BadRequestException("Employee id cannot be negative");
		}

		for (Employee employee : empSet) {
			if (employee.getEmpId() == empId)
				return employee;
		}
		throw new ResourceNotFoundException(
				"Employee not present with this emp Id");
	}

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/xml, application/json", produces = {
			"application/json", "application/xml" })
	@ResponseBody
	public Set<Employee> geEmployees() {
		return empSet;
	}

	@RequestMapping(value = "/{empId}", method = RequestMethod.PUT, headers = "Accept=application/xml, application/json", produces = {
			"application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@ResponseBody
	public Employee editEmployee(@RequestBody Employee emp,
			@PathVariable int empId) throws ResourceNotFoundException,
			BadRequestException {
		// Validation
		if (empId < 0) {
			throw new BadRequestException("Emp id cannot be negative");
		}
		for (Employee empEl : empSet) {
			if (empId == empEl.getEmpId()) {
				empEl.setEmpId(emp.getEmpId());
				empEl.setEmpName(emp.getEmpName());
				return empEl;
			}
		}
		throw new ResourceNotFoundException("Employee not found to edit");
	}

	@RequestMapping(value = "/{empId}", method = RequestMethod.DELETE, headers = "Accept=application/xml, application/json", produces = {
			"application/json", "application/xml" })
	@ResponseBody
	public boolean deleteEmployee(@PathVariable int empId)
			throws BadRequestException, ResourceNotFoundException {

		// Validation
		if (empId < 0) {
			throw new BadRequestException("Emp id cannot be negative");
		}
		for (Employee empEl : empSet) {
			if (empId == empEl.getEmpId()) {
				empSet.remove(empEl);
				return true;
			}
		}
		throw new ResourceNotFoundException("Employee not found to delete");
	}

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/xml, application/json", produces = {
			"application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	@ResponseBody
	public boolean createEmployee(@RequestBody Employee emp)
			throws BadRequestException, ForbiddenException {
		// Validation
		if (emp.getEmpId() < 0) {
			throw new BadRequestException(
					"Employee cannot be created with negative emp id");
		}
		for (Employee empEl : empSet) {
			if (emp.getEmpId() == empEl.getEmpId()) {
				throw new ForbiddenException("Duplicate emp id is not allwed");
			}
		}
		return empSet.add(emp);
	}

}
