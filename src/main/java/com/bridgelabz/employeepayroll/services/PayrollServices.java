package com.bridgelabz.employeepayroll.services;

import com.bridgelabz.employeepayroll.builder.PayrollBuilder;
import com.bridgelabz.employeepayroll.dto.PayrollDto;
import com.bridgelabz.employeepayroll.entity.EmployeePayroll;
import com.bridgelabz.employeepayroll.repository.PayrollRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    @Autowired
    private PayrollBuilder payrollBuilder;

    public String ADDED_DATA_SUCCESSFULLY = "ADDED payroll into database";
    public String DELETED_DATA_SUCCESSFULLY = "DELETED payroll from database";
    public String UPDATED_DATA_SUCCESSFULLY = "UPDATED payroll in database";

    /**
     * purpose : To list all payroll in database
     *
     * @return list : payroll has name, salary & gender for each entry.
     */
    public List<PayrollDto> getAllPayroll() {
        return payrollRepository
                .findAll()
                .stream()
                .map(employeePayrollData -> {
                    return modelMapper.map(employeePayrollData, PayrollDto.class);
                })
                .collect(Collectors.toList());
    }

    /**
     * Purpose : To add payroll ino database.
     *
     * @param payrollDto : New payroll entry with name, salary & gender
     * @return String : Success message for adding data into database.
     */
    public String addPayroll(PayrollDto payrollDto) {
        EmployeePayroll employeePayrollData = modelMapper.map(payrollDto, EmployeePayroll.class);
         payrollRepository.save(employeePayrollData);
         return ADDED_DATA_SUCCESSFULLY;
    }

    /**
     * Purpose : To delete payroll from database.
     *
     * @param id : Database id
     * @return String : Success message for deleting data from database.
     */
    public String deletePayroll(int id)  {
        EmployeePayroll employeePayroll = findDetails(id);
        payrollRepository.delete(employeePayroll);
        return DELETED_DATA_SUCCESSFULLY;
    }

    /**
     * Purpose : To update payroll entry with new one using id
     *
     * @param id : Database id
     * @param payrollDto : New payroll entry with name, salary & gender
     * @return String : Success message for updating data in database.
     */
    public String updatePayroll(int id, PayrollDto payrollDto) {
        EmployeePayroll employeePayroll = findDetails(id);
        employeePayroll = payrollBuilder.buildPayrollEntity(payrollDto, employeePayroll);
        payrollRepository.save(employeePayroll);
        return UPDATED_DATA_SUCCESSFULLY;
    }

    /**
     * purpose : To find entry by id if it presents in database
     *
     * @param id : Database id
     * @return employeePayroll : Data which has name, salary & gender or throwing exception
     */
    public EmployeePayroll findDetails(int id) {
        return payrollRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * purpose : To find entry by id if it presents in database
     *
     * @param id : Database id.
     * @return address : Data which has address details.
     */
    public PayrollDto getPayroll(int id) {
        EmployeePayroll employeePayroll = findDetails(id);
        return modelMapper.map(employeePayroll, PayrollDto.class);
    }
}
