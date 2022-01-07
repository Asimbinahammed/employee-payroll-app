package com.bridgelabz.employeepayroll.services;

import com.bridgelabz.employeepayroll.dto.PayrollDto;
import com.bridgelabz.employeepayroll.entity.EmployeePayroll;

import java.util.List;

/**
 * Purpose To Create Interface Class For Payroll Service
 *
 * @author : Kunal Suryawanshi
 * @since : 13-12-2021
 */
public interface IPayrollServices {
    List<PayrollDto> getAllPayroll();

    PayrollDto getPayroll(int empId);

    String addPayroll(PayrollDto empPayrollDTO);

    String updatePayroll(int id, PayrollDto employeeDto);

    String deletePayroll(int empId);

    EmployeePayroll findDetails(int empId);
}