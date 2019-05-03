package org.academiadecodigo.bootcamp.crudJpa.dao;

import org.academiadecodigo.bootcamp.crudJpa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository //that marks the specific class as a Data Access Object, thus clarifying it's role
public class EmployeeDAOJPAImpl implements EmployeeDAO {

    //define field for entityManager
    private EntityManager entityManager;

    //set up constructor injection
        //constructor injection (automatically created by spring boot)
    @Autowired
    public EmployeeDAOJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        //using native JPA API

        //create a query
        Query query = entityManager.createQuery("FROM Employee");

        //execute query and get result list
        List<Employee> employees = query.getResultList();

        //return the results
        return employees;
    }

    @Override
    public Employee findById(int id) {

        //using native JPA API

        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public void save(Employee employee) {

        //using native JPA API

        //save or update the employee
        Employee employeeDB = entityManager.merge(employee);

        //update with id from db
        employee.setId(employeeDB.getId());
    }

    @Override
    public void deleteById(int id) {

        //using native JPA API

        Query query = entityManager.createQuery("DELETE FROM Employee WHERE id=:idToDelete");
        query.setParameter("idToDelete", id);
        query.executeUpdate();
    }
}