package org.academiadecodigo.bootcamp.crudhibernate.service;

import org.academiadecodigo.bootcamp.crudhibernate.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    void save(Employee employee);

    void deleteById(int id);
}
