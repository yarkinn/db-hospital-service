import db_operations.EmployeeOperations;
import utils.*;
import entity.EmployeeEntity;

import javax.persistence.*;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;



import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static boolean hasDigits(String s){

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))){
                return true;
            }

        }
        return false;
    }
    public static void main(String[] args) {
        EmployeeOperations operations = new EmployeeOperations();
        System.out.println("Welcome to Hospital Employee Management System");
        boolean stop = false;
        while(!stop){
            try{
                System.out.println("Press 1 to add new employee\nPress 2 to remove employee\nPress 3 to list employees");
                System.out.print( "Your choice: ");
                Scanner in = new Scanner(System.in);
                int choice = in.nextInt();
                System.out.println();
                if( choice == 1){

                    System.out.println("Employee types to choose from: ");
                    System.out.println(Constants.jobs);
                    System.out.print("Please choose one of the above options' number: ");
                    int typeNumerated = in.nextInt();
                    while(typeNumerated > Constants.jobNumber || typeNumerated <= 0){
                        System.out.print("Invalid job input.Try again: ");
                        typeNumerated = in.nextInt();

                    }
                    System.out.print("Name of employee: ");
                    String name = in.next();
                    while( hasDigits(name)){
                        System.out.print("Employee name can't include numbers. New name: ");
                        name = in.next();
                    }
                    System.out.print("Salary of employee: ");
                    int salary = in.nextInt();
                    while( salary < 0  ){
                        System.out.print("Salary can't be negative,please enter a new salary: ");
                        salary = in.nextInt();
                    }
                    System.out.print("Working hour of employee: ");

                    int workingHours = in.nextInt();
                    while( workingHours < 0  ){
                        System.out.print("Working hours can't be negative,please enter a new working hour: ");
                        workingHours = in.nextInt();
                    }
                    String type = Constants.jobArr[typeNumerated - 1];
                    operations.addEmployee(name,type,salary,workingHours);
                }
                else if( choice == 2){
                    System.out.print("Id of the employee: ");
                    int id = in.nextInt();
                    if (operations.employeeExists(id)){
                        operations.removeEmployee(id);

                    }
                    else{
                        System.out.println("Employee with the specified id doesn't exist");
                    }

                } else if (choice == 3) {
                    operations.listEmployees();
                }
                System.out.print("If you want to continue type 1 else 0: ");
                choice = in.nextInt();
                if(choice == 0){
                    stop = true;
                }

            }catch( Exception e){
                System.out.println("Invalid input! Menu will turn to inital state");

            }

        }

    }
}