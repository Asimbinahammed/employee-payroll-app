package com.bridgelabz.employeepayroll.repository;

import com.bridgelabz.employeepayroll.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollRepository extends JpaRepository<Payroll, Integer> {

}

