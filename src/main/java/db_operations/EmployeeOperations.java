package db_operations;

import entity.EmployeeEntity;

import javax.persistence.*;
import java.util.List;

public class EmployeeOperations {
    public void addEmployee(String name,String job,int salary,int hours){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
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
            transaction.commit();
        }
        finally{
            if(transaction.isActive()){
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
    public void removeEmployee(int id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.createNativeQuery("delete from employee where id =" + id).executeUpdate();
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
    public void listEmployees(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
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

    public boolean employeeExists(int id) {
        boolean employeeExists = false;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
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



