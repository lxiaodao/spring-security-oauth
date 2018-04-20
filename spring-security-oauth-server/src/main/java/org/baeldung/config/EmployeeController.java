package org.baeldung.config;

import java.util.ArrayList;
import java.util.List;

import org.baeldung.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();

    @GetMapping("/employee")
    @ResponseBody
    public Optional<Employee> getEmployee(@RequestParam String email) {
        return employees.stream().filter(x -> x.getEmail().equals(email)).findAny();
    }

    @PostMapping(value = "/employee") //, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    @ResponseBody
    public Employee postMessage(@RequestBody Employee employee) {
        employees.add(employee);
       
        return employee;
    }
    
    @PostMapping(value = "/employee2")
    @ResponseBody
    public Employee mokeMessage(HttpServletRequest request) {
       
        return new Employee("2","22");
    }
    
  
}
