package com.ProjectBackend.Service;

import java.util.List;

import com.ProjectBackend.model.employee.Employee;

public interface EmployeeServices {

	public Employee addNewEmployee(Employee employee);

	public Employee getEmployee(String empId);

	public List<Employee> getAllEmployees();
}
