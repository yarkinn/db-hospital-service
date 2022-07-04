import db_operations.EmployeeOperations;
import entity.EmployeeEntity;

import javax.persistence.*;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

            try{
                transaction.begin();



                EmployeeEntity kaan = new EmployeeEntity();
                kaan.setName("adasd");
                kaan.setSalary(122234);
                kaan.setHours(24223);
                kaan.setJob("doctor");
                entityManager.persist(kaan);
                EmployeeEntity newEmp = new EmployeeEntity();
                newEmp.setName("adasd");
                newEmp.setJob("pt");
                newEmp.setHours(1231);
                newEmp.setSalary(123123);
                entityManager.persist(newEmp);



                Query query = entityManager.createNativeQuery("SELECT * FROM employee",EmployeeEntity.class);
                List list = query.getResultList();

                //System.out.println(Arrays.toString(list.toArray()));
                //entityManager.createNativeQuery("delete from employee where id = 2").executeUpdate();
                EmployeeOperations operations = new EmployeeOperations();
                operations.addEmployee("murat","doctor",2000,3500);
                operations.removeEmployee(4);
                operations.removeEmployee(4);
                operations.listEmployees();



                transaction.commit();

            }finally{
                if(transaction.isActive()){
                    transaction.rollback();
                }
                entityManager.close();
                entityManagerFactory.close();
            }




    }
}
