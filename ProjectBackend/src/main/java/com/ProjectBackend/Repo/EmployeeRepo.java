package com.ProjectBackend.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.ProjectBackend.model.employee.Employee;

@Component
public interface EmployeeRepo extends MongoRepository<Employee, String>
{

}
