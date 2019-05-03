package org.academiadecodigo.bootcamp.crudJpa.dto;

import org.academiadecodigo.bootcamp.crudJpa.entity.Employee;
import org.academiadecodigo.bootcamp.crudJpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDTOToEmployee {

    // DTO data transfer object

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee convert(EmployeeDto employeeDto) {

        Employee employee = (employeeDto.getId() != null ? employeeService.findById(employeeDto.getId()) : new Employee());

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        return employee;
    }
}