package com.bridgelabz.employeepayroll.builder;

import com.bridgelabz.employeepayroll.dto.PayrollDto;
import com.bridgelabz.employeepayroll.entity.EmployeePayroll;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Purpose : This is builder class which converts payroll dto to into entity.
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-14
 */
@Component
public class PayrollBuilder {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Purpose : To convert payroll dto into payroll entity.
     *
     * @param payrollDto : payroll dto which is to be converted.
     * @param employeePayroll : employee payroll entity which will be overwritten.
     * @return employeePayroll : converted employee payroll
     */
    public EmployeePayroll buildPayrollEntity(PayrollDto payrollDto, EmployeePayroll employeePayroll) {
        modelMapper.map(payrollDto, employeePayroll);
        return employeePayroll;
    }
}
