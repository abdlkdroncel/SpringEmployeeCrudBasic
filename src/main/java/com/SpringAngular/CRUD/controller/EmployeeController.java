package com.SpringAngular.CRUD.controller;

import com.SpringAngular.CRUD.model.Employee;
import com.SpringAngular.CRUD.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/employees")
    public List<Employee> gettAllEmployees(){
        return repository.findAll();
    }

    @PostMapping("/addemployee")
    public Employee createEmploye(@RequestBody Employee employee){
        return repository.save(employee);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee=repository.findById(id).orElseThrow(()->new ResolutionException("User Not Found"));
        return ResponseEntity.ok(employee);

    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
        Employee e=repository.findById(id).orElseThrow(()->new ResolutionException("User Not Found"));
        e.setFirstName(employee.getFirstName());
        e.setLastName(employee.getLastName());
        e.setEmailId(employee.getEmailId());

        return ResponseEntity.ok(repository.save(e));
    }
}
