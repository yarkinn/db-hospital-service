package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "hospital")
public class EmployeeEntity {
    @Id
    @Column(name = "id", updatable = false,nullable = false )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "salary")
    private Integer salary;
    @Basic
    @Column(name = "job")
    private String job;
    @Basic
    @Column(name = "hours")
    private Integer hours;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public EmployeeEntity(String name,String job, int salary, int hours) {
        this.job = job;
        this.name = name;
        this.salary = salary;
        this.hours = hours;
    }

    public EmployeeEntity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(salary, that.salary) && Objects.equals(job, that.job) && Objects.equals(hours, that.hours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, job, hours);
    }

    @Override
    public String toString() {
        return
                "Id: " + id +
                "\nName: " + name  +
                "\nSalary: " + salary +
                "\nJob: " + job +
                "\nHours: " + hours+ "\n";
    }



}
