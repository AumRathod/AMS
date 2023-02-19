package com.ProjectBackend.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ProjectBackend.Repo.EmployeeRepo;
import com.ProjectBackend.Service.EmployeeServices;
import com.ProjectBackend.model.LoginColl;
import com.ProjectBackend.model.employee.Employee;

@Service
public class EmployeeServicesImpl implements EmployeeServices
{
	@Autowired
	EmployeeRepo empRepo;

	@Autowired
	LoginColl lc;
	
	@Override
	public Employee getEmployee(String empId) {
		return empRepo.findById(empId).orElse(null);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return empRepo.findAll();
	}

	@Autowired
	private JavaMailSender mailSender;

	private String generateToken() {
		StringBuilder token = new StringBuilder();
		return token.append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString()).toString();
	}

	private void sendEmail(Employee emp,String token) {
		
		String body = "" + token; 						// have to provide the first time password page link
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emp.getEmail());
		message.setSubject("Login for First time into AMS.");
		message.setText(body);
		mailSender.send(message);
	}

	@Override
	public Employee addNewEmployee(Employee employee) { // It will store the emp details in employee collection as well
														// as into the loginColl and send the mail to the employee that
														// he has been added into the server and has to perform first
														// time login
		empRepo.save(employee);
		lc.setName(employee.getEmployeeFirstName());
		lc.setType("User");
		lc.setId(employee.getEmployeeId());
		String token = generateToken();
		lc.setToken(token);
		sendEmail(employee,token);
		return employee;
	}
}
