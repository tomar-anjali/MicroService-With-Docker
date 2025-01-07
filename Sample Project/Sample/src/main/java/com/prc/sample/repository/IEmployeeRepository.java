package com.prc.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prc.sample.entity.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer>{

}
