package com.prc.sample.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prc.sample.entity.Employee;
import com.prc.sample.entity.EmployeeRequestEntity;
import com.prc.sample.entity.EmployeeResponseEntity;
import com.prc.sample.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/save")
	public ResponseEntity<String> empSave(@RequestBody EmployeeRequestEntity employeeRequestEntity) {
		boolean isSaved = employeeService.empSave(employeeRequestEntity);
		if (isSaved) {
			return new ResponseEntity<>("Employee Saved Successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Employee Save Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get")
	public ResponseEntity<List<EmployeeResponseEntity>> getAllEmp() {
		List<Employee> employees = employeeService.getAllEmployee();

		if (!employees.isEmpty()) {
			List<EmployeeResponseEntity> employeeResponseEntities = employees.stream()
					.map(employee -> new EmployeeResponseEntity(employee)) // Use the constructor that maps Employee to
																			// EmployeeResponseEntity
					.collect(Collectors.toList());
			return new ResponseEntity<>(employeeResponseEntities, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<EmployeeResponseEntity> getEmpById(@PathVariable int id) {
		Optional<Employee> employee = employeeService.getEmployeeById(id);

		if (employee.isPresent()) {
			EmployeeResponseEntity employeeResponseEntity = new EmployeeResponseEntity(employee.get());
			return new ResponseEntity<>(employeeResponseEntity, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<EmployeeResponseEntity> updateEmployee(
            @PathVariable int id, 
            @RequestBody EmployeeRequestEntity employeeRequestEntity) {

        Optional<Employee> updatedEmployee = employeeService.updateEmployee(id, employeeRequestEntity);

        if (updatedEmployee.isPresent()) {
            Employee employee = updatedEmployee.get();
            EmployeeResponseEntity employeeResponseEntity = new EmployeeResponseEntity(
                    employee.getId(),
                    employee.getName(),
                    employee.getPosition()
            );
            return new ResponseEntity<>(employeeResponseEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmp(@PathVariable int id) {
		boolean isDeleted = employeeService.deleteEmployee(id);
		if (isDeleted) {
			return new ResponseEntity<String>("Employee Removed Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Employee Data NOT found", HttpStatus.NOT_FOUND);
		}
	}
}
