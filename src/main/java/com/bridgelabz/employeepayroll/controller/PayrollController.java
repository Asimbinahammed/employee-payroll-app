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
    public String welcomeMessage = "Success, Welcome to employee payroll app";

    @Autowired
    private PayrollServices payrollServices;

    /**
     * Purpose : To print welcome message.
     * @return ResponseEntity : Having welcomes message.
     */
    @RequestMapping(value = {"", "/"})
    public ResponseEntity getEmployeePayrollData() {
        return new ResponseEntity(welcomeMessage,null , HttpStatus.OK);
    }

    /**
     * Purpose : To get list of all employee's from database.
     * @return List : List of employee's.
     */
    @GetMapping(value = "/employee")
    public List<PayrollDto> getAllPayroll() {
        return payrollServices.getAllPayroll();
    }

    /**
     * Purpose : To add employee into database.
     * @param payrollDto : Having input data to be added into database.
     * @return ResponseEntity : Having success message, Added data &
     *                          success status response code indicates that the request has succeeded.
     */
    @PostMapping(value = "/employee")
    public ResponseEntity addPayroll(@Valid @RequestBody PayrollDto payrollDto) {
        String successMessage = payrollServices.addPayroll(payrollDto);
        return new ResponseEntity(successMessage, payrollDto, HttpStatus.OK);
    }

    /**
     * Purpose : To update employee in database.
     * @param id : Database id which has tobe updated.
     * @param payrollDto : Having input data to be updated into database.
     * @return ResponseEntity : Having success message, updated data &
     *                          success status response code indicates that the request has succeeded.
     */
    @PutMapping(value = "/employee/{id}")
    public ResponseEntity updatePayroll(
            @PathVariable(name = "id") int id,
            @Valid @RequestBody PayrollDto payrollDto) {
        String updateMessage = payrollServices.updatePayroll(id, payrollDto);
        return new ResponseEntity(updateMessage, payrollDto, HttpStatus.OK);
    }

    /**
     * Purpose : To delete employee from database
     * @param id : Database id which has tobe deleted.
     * @return ResponseEntity : Having success message, deleted data &
     *                          success status response code indicates that the request has succeeded.
     */
    @DeleteMapping(value = "employee/{id}")
    public ResponseEntity deletePayroll(@PathVariable(name = "id") int id) {
        EmployeePayroll empData = payrollServices.findDetails(id);
        String deleteMessage = payrollServices.deletePayroll(id);
        return new ResponseEntity(deleteMessage, empData, HttpStatus.OK);
    }
}
