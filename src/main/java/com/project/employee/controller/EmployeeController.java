package com.project.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.employee.model.Employee;
import com.project.employee.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService empService;

//    Create employee
    @RequestMapping(value="/employees", method=RequestMethod.POST)
    public Employee createEmployee(@RequestBody Employee emp) {
        return empService.createEmployee(emp);
    }
//    Get all employees
    @RequestMapping(value="/employees", method=RequestMethod.GET)
    public List<Employee> readEmployees() {
        return empService.getEmployees();
    }
//    Get employee by id
    @RequestMapping(value="/employees/{empId}", method=RequestMethod.GET)
    public Employee readEmployees(@PathVariable(value = "empId") Long id) {
        return empService.getEmployeeById(id);
    }
//    Update employee
    @RequestMapping(value="/employees/{empId}", method=RequestMethod.PUT)
    public Employee readEmployees(@PathVariable(value = "empId") Long id, @RequestBody Employee empDetails) {
        return empService.updateEmployee(id, empDetails);
    }
//    delete employee
    @RequestMapping(value="/employees/{empId}", method=RequestMethod.DELETE)
    public void deleteEmployees(@PathVariable(value = "empId") Long id) {
        empService.deleteEmployee(id);
    }


}
