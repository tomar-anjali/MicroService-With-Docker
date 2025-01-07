package com.prc.sample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prc.sample.entity.Employee;
import com.prc.sample.entity.EmployeeRequestEntity;
import com.prc.sample.repository.IEmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepository;

	public boolean empSave(EmployeeRequestEntity employeeRequestEntity) {
		Employee employee = new Employee();
		employee.setName(employeeRequestEntity.getName());
		employee.setPosition(employeeRequestEntity.getPosition());

		try {
			employeeRepository.save(employee);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Optional<Employee> getEmployeeById(int id) {
		return employeeRepository.findById(id);

	}

	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	public boolean deleteEmployee(int id) {

		if (employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public Optional<Employee> updateEmployee(int id, EmployeeRequestEntity employeeRequestEntity) {
        Optional<Employee> existingEmployee = getEmployeeById(id);
        
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();

            employee.setName(employeeRequestEntity.getName());
            employee.setPosition(employeeRequestEntity.getPosition());

            employeeRepository.save(employee);

            return Optional.of(employee);
        } else {
            return Optional.empty();  
        }
    }
}
