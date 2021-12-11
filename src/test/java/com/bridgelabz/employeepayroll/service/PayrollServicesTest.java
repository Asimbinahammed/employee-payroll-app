package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.controller.PayrollController;
import com.bridgelabz.employeepayroll.dto.PayrollDto;
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
public class PayrollServicesTest {


    @InjectMocks
    private PayrollController payrollController;
    @Mock
    private PayrollServices payrollServices;
    
    @Test
    void given2payrollDto_whenGetAllAtmCalled_shouldReturnListOfpayrollDto() {
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
}