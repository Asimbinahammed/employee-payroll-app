package com.bridgelabz.employeepayroll.controller;

import com.bridgelabz.employeepayroll.dto.PayrollDto;
import com.bridgelabz.employeepayroll.entity.ResponseEntity;
import com.bridgelabz.employeepayroll.services.PayrollServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PayrollControllerTest {

    @InjectMocks
    private PayrollController payrollController;
    @Mock
    private PayrollServices payrollServices;

    @Test
    void whenCalledGetEmployeePayrollData_shouldReturnResponseEntity() {
        String successMessage = "Success, Welcome to employee payroll app";
        ResponseEntity responseEntity = payrollController
                .getWelcomeMessage();
        Assertions.assertEquals(successMessage, responseEntity.getMessage());
    }

    @Test
    void given2payrollDto_whenGetAllPayrollCalled_shouldReturnListOfPayrollDto() {
        List<PayrollDto> payrollDtoList = new ArrayList<>();
        PayrollDto payrollDto = new PayrollDto();
        payrollDto.setName("Asim Ahammed");
        payrollDto.setGender("M");
        payrollDto.setSalary(23455);
        payrollDtoList.add(payrollDto);
        PayrollDto payrollDto2 = new PayrollDto();
        payrollDto2.setName("Siva");
        payrollDto2.setGender("M");
        payrollDto2.setSalary(3456);
        payrollDtoList.add(payrollDto2);
        when(payrollServices.getAllPayroll()).thenReturn(payrollDtoList);
        List<PayrollDto> actualResponse = payrollController.getAllPayroll();
        for (int i = 0; i < actualResponse.size(); i++) {
            Assertions.assertEquals(payrollDtoList.get(i).getName(), actualResponse.get(i).getName());
            Assertions.assertEquals(payrollDtoList.get(i).getGender(), actualResponse.get(i).getGender());
            Assertions.assertEquals(payrollDtoList.get(i).getSalary(), actualResponse.get(i).getSalary());
        }
    }

    @Test
    void givenPayrollDto_whenCalledAddPayroll_shouldReturnResponseEntity() {
        String successMessage = "ADDED atm into database";
        PayrollDto payrollDto = new PayrollDto();
        payrollDto.setName("Asim Ahammed");
        payrollDto.setGender("M");
        payrollDto.setSalary(23455);
        when(payrollServices.addPayroll(payrollDto)).thenReturn(successMessage);
        ResponseEntity responseEntity = payrollController.addPayroll(payrollDto);
        Assertions.assertEquals(successMessage, responseEntity.getMessage());
        Assertions.assertEquals(payrollDto, responseEntity.getData());
    }

    @Test
    void givenPayrollDto_whenCalledUpdatePayroll_shouldReturnResponseEntity() {
        String successMessage = "UPDATED atm in database";
        int id =1;
        PayrollDto payrollDto = new PayrollDto();
        payrollDto.setName("Asim Ahammed");
        payrollDto.setGender("M");
        payrollDto.setSalary(23455);
        when(payrollServices.updatePayroll(id, payrollDto)).thenReturn(successMessage);
        ResponseEntity responseEntity = payrollController.updatePayroll(id, payrollDto);
        Assertions.assertEquals(successMessage, responseEntity.getMessage());
        Assertions.assertEquals(payrollDto, responseEntity.getData());
    }

    @Test
    void givenId_whenCalledDeletePayroll_shouldReturnResponseEntity() {
        String successMessage = "DELETED atm from database";
        int id =1;
        PayrollDto payrollDto = new PayrollDto();
        payrollDto.setName("Asim Ahammed");
        payrollDto.setGender("M");
        payrollDto.setSalary(23455);
        when(payrollServices.getPayroll(id)).thenReturn(payrollDto);
        when(payrollServices.deletePayroll(id)).thenReturn(successMessage);
        ResponseEntity responseEntity = payrollController.deletePayroll(id);
        Assertions.assertEquals(successMessage, responseEntity.getMessage());
    }

    @Test
    void givenId_whenCalledGetPayroll_shouldReturnResponseEntity() {
        String successMessage = "The address for the given id is here : ";
        int id =1;
        PayrollDto payrollDto = new PayrollDto();
        payrollDto.setName("Asim Ahammed");
        payrollDto.setGender("M");
        payrollDto.setSalary(23455);
        when(payrollServices.getPayroll(id)).thenReturn(payrollDto);
        ResponseEntity responseEntity = payrollController.getPayroll(id);
        Assertions.assertEquals(successMessage, responseEntity.getMessage());
    }
}
