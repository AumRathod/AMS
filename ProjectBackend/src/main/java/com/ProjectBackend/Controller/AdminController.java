package com.ProjectBackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjectBackend.Service.AssetServices;
import com.ProjectBackend.Service.EmployeeServices;
import com.ProjectBackend.model.asset.AddAssetClassInfo;
import com.ProjectBackend.model.asset.AddAssetInfo;
import com.ProjectBackend.model.asset.AssetClass;
import com.ProjectBackend.model.asset.AssetInfo;
import com.ProjectBackend.model.employee.Employee;

@RequestMapping("/admin")
@RestController
public class AdminController {

	@Autowired
	AssetServices assetServices;

	@Autowired
	EmployeeServices employeeServices;

	@GetMapping("")
	public ResponseEntity<String> testConnection() {
		System.out.println("hello.");
		return new ResponseEntity<String>("Hello", HttpStatus.OK);

	}

	@GetMapping("/assetKeys/{name}")
	public ResponseEntity<AssetClass> provideKeys(@PathVariable String name) {

		AssetClass ret = assetServices.getAssetClass(name);

		return new ResponseEntity<AssetClass>(ret, HttpStatus.OK);
	}

	@PostMapping("/addAsset")
	public ResponseEntity<HttpStatus> addAsset(@RequestBody AddAssetInfo assetInfo) {
		assetServices.insertNewAsset(assetInfo);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/addAssetClass")
	public ResponseEntity<HttpStatus> addAssetClass(@RequestBody AddAssetClassInfo assetClassInfo) {
		assetServices.insertNewAssetClass(assetClassInfo);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getAllClasses")
	public ResponseEntity<List<AssetClass>> getAllClasses() {
		return new ResponseEntity<List<AssetClass>>(assetServices.getAllAssetClasses(), HttpStatus.OK);
	}

	@GetMapping("/getAllAssets")
	public ResponseEntity<List<AssetInfo>> getAllAssets() {
		return new ResponseEntity<List<AssetInfo>>(assetServices.getAllAssetsInfo(), HttpStatus.OK);
	}

	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee ret = employeeServices.addNewEmployee(employee);
		if (ret != null)
			return new ResponseEntity<Employee>(ret, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/getEmployee/{empId}")
	public ResponseEntity<Employee> getEmployeeDetails(@PathVariable String empId) {
		Employee ret = employeeServices.getEmployee(empId);
		if (ret == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Employee>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		return new ResponseEntity<List<Employee>>(employeeServices.getAllEmployees(),HttpStatus.OK);
	}
}
