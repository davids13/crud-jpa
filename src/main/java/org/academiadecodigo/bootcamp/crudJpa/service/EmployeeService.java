package org.academiadecodigo.bootcamp.crudJpa.service;

import org.academiadecodigo.bootcamp.crudJpa.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    void save(Employee employee);

    void deleteById(int id);
}
