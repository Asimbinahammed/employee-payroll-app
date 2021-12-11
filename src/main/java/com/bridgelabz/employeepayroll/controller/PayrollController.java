package com.bridgelabz.employeepayroll.controller;

import com.bridgelabz.employeepayroll.dto.PayrollDto;
import com.bridgelabz.employeepayroll.entity.EmployeePayroll;
import com.bridgelabz.employeepayroll.entity.ResponseEntity;
import com.bridgelabz.employeepayroll.services.PayrollServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Purpose : To demonstrate different HTTP methods
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-03
 */
@RestController
@RequestMapping(value = "/payroll")
public class PayrollController {

    @Autowired
    private PayrollServices payrollServices;

    @RequestMapping(value = {"", "/"})
    public ResponseEntity getEmployeePayrollData() {
        return new ResponseEntity("Get Call Success",null , HttpStatus.OK);
    }

    @GetMapping(value = "/employee")
    public List<PayrollDto> getAllPayroll() {
        return payrollServices.getAllPayroll();
    }

    @PostMapping(value = "/employee")
    public ResponseEntity addPayroll(@Valid @RequestBody PayrollDto payrollDto) {
        EmployeePayroll empData = payrollServices.addPayroll(payrollDto);
        return new ResponseEntity("Created Employee Payroll Data : " , empData, HttpStatus.OK);
    }

    @PutMapping(value = "/employee/{id}")
    public ResponseEntity updatePayroll(
            @PathVariable(name = "id") int id,
            @Valid @RequestBody PayrollDto payrollDto) {
        EmployeePayroll empData = payrollServices.updatePayroll(id, payrollDto);
        return new ResponseEntity("Updated Employee Payroll Data : ", empData, HttpStatus.OK);
    }

    @DeleteMapping(value = "employee/{id}")
    public ResponseEntity deletePayroll(@PathVariable(name = "id") int id) {
        EmployeePayroll empData = payrollServices.deletePayroll(id);
        return new ResponseEntity("Deleted Employee Payroll Data : ", empData, HttpStatus.OK);
    }
}
