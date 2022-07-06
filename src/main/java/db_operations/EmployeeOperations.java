package db_operations;

import entity.EmployeeEntity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeOperations {
    private EntityManagerFactory getEntityManagerFactory(String password) {
        return Persistence.createEntityManagerFactory( "default",
                getProperties(password) );
    }

    private Map getProperties(String password) {
        Map result = new HashMap();

        result.put( "javax.persistence.jdbc.password", password );

        return result;
    }
    public void addEmployee(String name,String job,int salary,int hours,String password){

        EntityManagerFactory entityManagerFactory = getEntityManagerFactory(password);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            EmployeeEntity employee = new EmployeeEntity();
            employee.setName(name);
            employee.setSalary(salary);
            employee.setHours(hours);
            employee.setJob(job);
            entityManager.persist(employee);
            System.out.println("Employee with specified values have been added. The id of employee is: " + employee.getId());

            transaction.commit();
        }
        catch (Exception e){
            System.out.println("Employee can't be added");
        }
        finally{
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
    public void removeEmployee(int id,String password){
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory(password);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.createNativeQuery("delete from employee where id =" + id).executeUpdate();
            System.out.println("Employee with id " + id + " has been deleted.");
            transaction.commit();
        }
        catch (Exception e){
            System.out.println("Employee with the specified id doesn't exist");
        }
        finally{
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

    }
    public void listEmployees(String password){
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory(password);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            Query query = entityManager.createNativeQuery("SELECT * FROM employee",EmployeeEntity.class);
            List list = query.getResultList();
            if (list.isEmpty()){
                System.out.println("There are not any employees to list");
            }
            else{
                for (Object e:
                        list) {
                    System.out.println(e);

                }

            }

            transaction.commit();
        }
        catch (Exception e){
            System.out.println("There are not any employees to list");
        }
        finally{
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

    }

    public boolean employeeExists(int id,String password) {
        boolean employeeExists = false;
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory(password);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            Query query = entityManager.createNativeQuery("SELECT * FROM employee where id = "+ id,EmployeeEntity.class);
            List list = query.getResultList();
            if(!list.isEmpty()) {
                employeeExists = true;
            }
            transaction.commit();
        }
        catch (Exception e){
            System.out.println("There are not any employees to list");
        }
        finally{
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        return employeeExists;
    }


}



