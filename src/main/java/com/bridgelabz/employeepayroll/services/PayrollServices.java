package com.bridgelabz.employeepayroll.services;

import com.bridgelabz.employeepayroll.dto.PayrollDto;
import com.bridgelabz.employeepayroll.entity.EmployeePayroll;
import com.bridgelabz.employeepayroll.exception.ResourceNotFoundException;
import com.bridgelabz.employeepayroll.repository.PayrollRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Purpose : To demonstrate business logic which implements all the methods in controller layer
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-03
 */
@Service
public class PayrollServices {

    @Autowired
    private PayrollRepository payrollRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * purpose :  To list all payroll in database
     *
     * @return list : payroll  with car number, cardholder name, CVV for each entry.
     */
    public List<PayrollDto> getAllPayroll() {
        return payrollRepository
                .findAll()
                .stream()
                .map(employeePayrollData -> {
                    PayrollDto payrollDto = modelMapper.map(employeePayrollData, PayrollDto.class);
                    return payrollDto;
                })
                .collect(Collectors.toList());
    }

    /**
     * Purpose : To add payroll ino database.
     *
     * @param payrollDto : Input json message to add into database
     * @return String : Message for success.
     */
    public EmployeePayroll addPayroll(PayrollDto payrollDto) {
        EmployeePayroll employeePayrollData = modelMapper.map(payrollDto, EmployeePayroll.class);
        return payrollRepository.save(employeePayrollData);
    }

    /**
     * Purpose : To delete payroll from database.
     *
     * @param id : Database id
     * @return String : Success message if deleted the entry
     */
    public EmployeePayroll deletePayroll(int id) throws ResourceNotFoundException {
        EmployeePayroll employeePayroll = payrollRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found"));
        payrollRepository.delete(employeePayroll);
        return employeePayroll;
    }

    /**
     * Purpose : To update payroll entry with new one using id
     *
     * @param id         : Database id
     * @param payrollDto : New payroll entry with card number, cardHolder name & CVV
     * @return String : Success message if updated the entry
     */
    public EmployeePayroll updatePayroll(int id, PayrollDto payrollDto) {
        EmployeePayroll employeePayroll = payrollRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found"));
        modelMapper.map(payrollDto, employeePayroll);
        return payrollRepository.save(employeePayroll);
    }

}
