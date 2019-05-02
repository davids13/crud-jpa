package org.academiadecodigo.bootcamp.crudhibernate.controller.rest;

import org.academiadecodigo.bootcamp.crudhibernate.entity.Employee;
import org.academiadecodigo.bootcamp.crudhibernate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Annotating a class with @RestController is the same as annotating it with both @Controller and @ResponseBody
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    //set up constructor injection
        // constructor injection (automatically created by spring boot)
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    /**
     * @param id the employee id
     * @return the response entity
     *
     * The ResponseEntity class represents an HTTP response, consisting of headers,
     * body and response code.
     * It is used to return a specific http status code from the controller.
     */
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id) {

        Employee employee = employeeService.findById(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    //@PathVariable annotation can be used on a controller method argument to bind it to the value of a URI template variable:
    //@RequestBody can be used in a controller to implement smart object serialization and deserialization
    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {

        employee.setId(0);
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Integer id) {

        employee.setId(id);
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Integer id) {

        try {
            employeeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}