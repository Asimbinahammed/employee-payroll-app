package com.bridgelabz.employeepayroll.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Purpose : To declare inputs for connecting with database.
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-03
 */
@Entity
@Table(name = "employee_payroll")
@Getter
@Setter
public class EmployeePayroll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String gender;
    private long salary;
    @CreationTimestamp
    private LocalDateTime Start;

}
