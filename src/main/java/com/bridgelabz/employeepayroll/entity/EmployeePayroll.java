package com.bridgelabz.employeepayroll.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee_payroll")
@Getter
@Setter
public class EmployeePayroll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private Character gender;
    private long salary;
    @CreationTimestamp
    private Date Start;

}
