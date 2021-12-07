package com.bridgelabz.employeepayroll.controller;

import com.bridgelabz.employeepayroll.dto.PayrollDto;
import com.bridgelabz.employeepayroll.services.PayrollServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import com.bridgelabz.employeepayroll.exception.ResourceNotFoundException;


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

    @GetMapping(value = "get-all")
    public List<PayrollDto> getAllpayroll(){
        return payrollServices.getAllPayroll();
    }

    @PostMapping(value = "/add")
    public String addpayroll(@Valid @RequestBody PayrollDto payrollDto){
        return payrollServices.addPayroll(payrollDto);
    }

    @PutMapping(value = "/update/{id}")
    public String updatepayroll(@Valid
                            @PathVariable(name = "id") int id,
                            @RequestBody PayrollDto payrollDto){
        return payrollServices.updatePayroll(id, payrollDto);
    }

    @DeleteMapping(value = "delete/{id}")
    public String deletepayroll(@PathVariable(name = "id") int id){
        return payrollServices.deletePayroll(id);
    }

}
