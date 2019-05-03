package org.academiadecodigo.bootcamp.crudJpa.dao;

import org.academiadecodigo.bootcamp.crudJpa.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository //that marks the specific class as a Data Access Object, thus clarifying it's role
public class EmployeeDAOImpl implements EmployeeDAO {

    //define field for entityManager
    private EntityManager entityManager;

    //set up constructor injection
        // constructor injection (automatically created by spring boot)
    @Autowired
    public EmployeeDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //create a query
        //using native Hibernate API
        Query<Employee> query = currentSession.createQuery("FROM Employee", Employee.class);
        //execute query and get result list
        List<Employee> employees = query.getResultList();
        //return the results
        return employees;
    }

    @Override
    public Employee findById(int id) {

        int theId = id;
        Session currentSession = entityManager.unwrap(Session.class);
        Employee employee = currentSession.get(Employee.class, theId);
        return employee;
    }

    @Override
    public void save(Employee employee) {

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(employee);
    }

    @Override
    public void deleteById(int id) {

        int theId = id;
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Employee> query = currentSession.createQuery("DELETE FROM Employee WHERE id=:idToDelete");
        query.setParameter("idToDelete", id);
        query.executeUpdate();
    }
}