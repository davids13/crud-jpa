package org.academiadecodigo.bootcamp.crudhibernate.dao;

import org.academiadecodigo.bootcamp.crudhibernate.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(int id);

    void save(Employee employee);

    void deleteById(int id);
}
