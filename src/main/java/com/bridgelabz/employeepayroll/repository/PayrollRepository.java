package com.bridgelabz.employeepayroll.repository;

import com.bridgelabz.employeepayroll.entity.EmployeePayroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollRepository extends JpaRepository<EmployeePayroll, Integer> {

}

