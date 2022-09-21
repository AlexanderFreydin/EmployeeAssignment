package com.project.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.employee.model.Employee;
import com.project.employee.repository.EmployeeRepository;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepository;

    // use employeeRepository object to create, update, read and delete employees which are represented by Employee (model) object
    // CREATE
    public Employee createEmployee(Employee emp) {
        return empRepository.save(emp);
    }

    // GET
    public List<Employee> getEmployees() {
        List<Employee> a = empRepository.findAll();
        return empRepository.findAll();
    }
    // GET BY ID
    public Employee getEmployeeById(Long empId) {
        return empRepository.findById(empId).get();
    }

    // DELETE
    public void deleteEmployee(Long empId) {
        empRepository.deleteById(empId);
    }

    // UPDATE
    public Employee updateEmployee(Long empId, Employee employeeDetails) {
        Employee emp = empRepository.findById(empId).get();
        emp.setFirstName(employeeDetails.getFirstName());
        emp.setLastName(employeeDetails.getLastName());
        emp.setEmail(employeeDetails.getEmail());

        return empRepository.save(emp);
    }
}
