package com.project.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.project.employee.model.Employee;
import com.project.employee.service.EmployeeService;

@Controller
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService empService;

//    Create employee
    @RequestMapping(value="/employees", method=RequestMethod.POST)
    @ResponseBody
    public Employee createEmployee(@RequestBody Employee emp) {
        return empService.createEmployee(emp);
    }
//    Get all employees
    @RequestMapping(value="/employees", method=RequestMethod.GET)
    @ResponseBody
    public List<Employee> readEmployees() {
        return empService.getEmployees();
    }
//    Get employee by id
    @RequestMapping(value="/employees/{empId}", method=RequestMethod.GET)
    @ResponseBody
    public Employee readEmployees(@PathVariable(value = "empId") Long id) {
        return empService.getEmployeeById(id);
    }
//    Update employee
    @RequestMapping(value="/employees/{empId}", method=RequestMethod.PUT)
    @ResponseBody
    public Employee readEmployees(@PathVariable(value = "empId") Long id, @RequestBody Employee empDetails) {
        return empService.updateEmployee(id, empDetails);
    }
//    delete employee
    @RequestMapping(value="/employees/{empId}", method=RequestMethod.DELETE)
    @ResponseBody
    public void deleteEmployees(@PathVariable(value = "empId") Long id) {
        empService.deleteEmployee(id);
    }

    //    Test
    @RequestMapping(value="/test", method=RequestMethod.POST)
    @ResponseBody
    public customResponse test(@RequestBody Employee emp) {
        customResponse customRes = new customResponse();
        try {
            Employee createdEmp = empService.createEmployee(emp);
            customRes.setMessage("Successfully added employee");
        }
        catch (Exception e){
            customRes.setMessage(e.getMessage());
        }
        return customRes;
    }

//    Request for UI
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }


}


class customResponse{
    private String message;

    public customResponse(){
        this.message = "";
    }
    public customResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
