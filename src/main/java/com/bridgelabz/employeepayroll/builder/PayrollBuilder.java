package com.bridgelabz.employeepayroll.builder;

import com.bridgelabz.employeepayroll.dto.PayrollDto;
import com.bridgelabz.employeepayroll.entity.EmployeePayroll;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PayrollBuilder {
    @Autowired
    private ModelMapper modelMapper;

    public EmployeePayroll buildPayrollEntity(PayrollDto payrollDto, EmployeePayroll employeePayroll) {
        modelMapper.map(payrollDto, employeePayroll);
        return employeePayroll;
    }
}
