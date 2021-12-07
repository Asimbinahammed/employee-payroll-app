package com.bridgelabz.employeepayroll.services;

import com.bridgelabz.employeepayroll.dto.PayrollDto;
import com.bridgelabz.employeepayroll.entity.Payroll;
import com.bridgelabz.employeepayroll.exception.ResourceNotFoundException;
import com.bridgelabz.employeepayroll.repository.PayrollRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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

    private static final String payroll_ADDED_SUCCESSFULLY = "payroll Added Successfully";
    private static final String payroll_EDITED_SUCCESSFULLY = "payroll Updated Successfully";
    private static final String payroll_DELETED_SUCCESSFULLY = "payroll Deleted Successfully";

    @Autowired
    private PayrollRepository payrollRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * purpose :  To list all payroll in database
     * @return list : payroll  with car number, cardholder name, CVV for each entry.
     */
    public List<PayrollDto> getAllPayroll() {
        return payrollRepository
                .findAll()
                .stream()
                .map(payroll -> {
                    PayrollDto payrollDto = modelMapper.map(payroll, PayrollDto.class);
                    return payrollDto;
                })
                .collect(Collectors.toList());
    }

    /**
     * Purpose : To add payroll ino database.
     * @param payrollDto : Input json message to add into database
     * @return String : Message for success.
     */
    public String addPayroll(PayrollDto payrollDto) {
        Payroll payroll = modelMapper.map(payrollDto, Payroll.class);
        payrollRepository.save(payroll);
        return payroll_ADDED_SUCCESSFULLY;
    }

    /**
     * Purpose : To delete payroll from database.
     * @param id : Database id
     * @return String : Success message if deleted the entry
     */
    public String deletePayroll(int id) throws ResourceNotFoundException {
        Optional<Payroll> payrollEntity = payrollRepository.findById(id);
        if (payrollEntity.isPresent()) {
            payrollRepository.delete(payrollEntity.get());
            return payroll_DELETED_SUCCESSFULLY;
        }
        throw new ResourceNotFoundException("User not found");
    }

    /**
     * Purpose : To update an payroll entry with new one using id
     * @param id : Database id
     * @param payrollDto : New payroll entry with card number, cardHolder name & CVV
     * @return String : Success message if updated the entry
     */
    public String updatePayroll(int id, PayrollDto payrollDto) {
        Optional<Payroll> payrollRecords = payrollRepository.findById(id);
        if (payrollRecords.isPresent()) {
            Payroll payroll = payrollRecords.get();
            modelMapper.map(payrollDto, payroll);
            payrollRepository.save(payroll);
            return payroll_EDITED_SUCCESSFULLY;
        }
        throw new ResourceNotFoundException("User not found");
    }


}
