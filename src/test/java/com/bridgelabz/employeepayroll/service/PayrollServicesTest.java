package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.builder.PayrollBuilder;
import com.bridgelabz.employeepayroll.dto.PayrollDto;
import com.bridgelabz.employeepayroll.entity.EmployeePayroll;
import com.bridgelabz.employeepayroll.repository.PayrollRepository;
import com.bridgelabz.employeepayroll.services.PayrollServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PayrollServicesTest {

    @InjectMocks
    private PayrollServices payrollServices;
    @Mock
    private PayrollRepository payrollRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PayrollBuilder payrollBuilder;

    @Test
    void given2payrollDtoInRepo_whenCalledGetAllPayroll_shouldReturnListOfPayrollDto() {
        List<PayrollDto> payrollDtoList = new ArrayList<>();
        PayrollDto payrollDto = new PayrollDto();
        payrollDto.setName("Asim Ahammed");
        payrollDto.setGender("F");
        payrollDto.setSalary(321000);
        payrollDtoList.add(payrollDto);
        PayrollDto payrollDto2 = new PayrollDto();
        payrollDto2.setName("Messi");
        payrollDto2.setGender("M");
        payrollDto2.setSalary(524000);
        payrollDtoList.add(payrollDto2);

        List<EmployeePayroll> payrollList = new ArrayList<>();
        EmployeePayroll employeePayroll = new EmployeePayroll();
        employeePayroll.setId(1);
        employeePayroll.setName("Asim Ahammed");
        employeePayroll.setGender("F");
        employeePayroll.setSalary(321000);
        employeePayroll.setStart(LocalDateTime.now());
        payrollList.add(employeePayroll);
        EmployeePayroll payroll2 = new EmployeePayroll();
        payroll2.setId(2);
        payroll2.setName("Messi");
        payroll2.setGender("M");
        payroll2.setSalary(524000);
        payroll2.setStart(LocalDateTime.now());
        payrollList.add(payroll2);

        when(payrollRepository.findAll()).thenReturn(payrollList);
        when(modelMapper.map(payrollList.get(0), PayrollDto.class)).thenReturn(payrollDto);
        when(modelMapper.map(payrollList.get(1), PayrollDto.class)).thenReturn(payrollDto2);
        List<PayrollDto> actualListOfPayroll = payrollServices.getAllPayroll();
        Assertions.assertEquals(2, actualListOfPayroll.size());
        Assertions.assertEquals(payrollDtoList, actualListOfPayroll);
    }

    @Test
    void givenPayrollDto_whenCalledAddPayroll_shouldReturnSuccessMessage() {
        String successMessage = "ADDED atm into database";
        PayrollDto payrollDto = new PayrollDto();
        payrollDto.setName("Asim Ahammed");
        payrollDto.setGender("F");
        payrollDto.setSalary(321000);

        EmployeePayroll employeePayroll = new EmployeePayroll();
        employeePayroll.setId(1);
        employeePayroll.setName("Asim Ahammed");
        employeePayroll.setGender("F");
        employeePayroll.setSalary(321000);
        employeePayroll.setStart(LocalDateTime.now());

        when(modelMapper.map(payrollDto, EmployeePayroll.class)).thenReturn(employeePayroll);
        String actualMessage = payrollServices.addPayroll(payrollDto);
        Assertions.assertEquals(successMessage, actualMessage);
        verify(payrollRepository, times(1)).save(employeePayroll);
    }

    @Test
    void givenIdAndPayrollDto_whenCalledUpdatePayroll_shouldReturnSuccessMessage() {
        int id = 1;
        ArgumentCaptor<EmployeePayroll> employeePayrollArgumentCaptor = ArgumentCaptor.forClass(EmployeePayroll.class);
        String expectedMessage = "UPDATED atm in database";
        PayrollDto payrollDto = new PayrollDto();
        payrollDto.setName("Asim Ahammed");
        payrollDto.setGender("M");
        payrollDto.setSalary(321000);

        EmployeePayroll employeePayroll = new EmployeePayroll();
        employeePayroll.setId(1);
        employeePayroll.setName("Asim Ahammed");
        employeePayroll.setGender("M");
        employeePayroll.setSalary(321000);
        employeePayroll.setStart(LocalDateTime.now());

        when(payrollRepository.findById(id)).thenReturn(Optional.of(employeePayroll));
        when(payrollBuilder.buildPayrollEntity(payrollDto, employeePayroll)).thenReturn(employeePayroll);
        String actualMessage = payrollServices.updatePayroll(id, payrollDto);
        Assertions.assertEquals(expectedMessage, actualMessage);
        verify(payrollRepository, times(1)).save(employeePayrollArgumentCaptor.capture());
        Assertions.assertEquals(payrollDto.getName(), employeePayrollArgumentCaptor.getValue().getName());
        Assertions.assertEquals(payrollDto.getSalary(), employeePayrollArgumentCaptor.getValue().getSalary());
        Assertions.assertEquals(payrollDto.getGender(), employeePayrollArgumentCaptor.getValue().getGender());
    }

    @Test
    void givenIdAndPayrollDto_whenCalledUpdatePayroll_shouldThrowEntityNotFoundException() {
        int id = 1;
        PayrollDto payrollDto = new PayrollDto();
        payrollDto.setName("Asim Ahammed");
        payrollDto.setGender("M");
        payrollDto.setSalary(321000);

        EmployeePayroll employeePayroll = new EmployeePayroll();
        employeePayroll.setId(1);
        employeePayroll.setName("Asim Ahammed");
        employeePayroll.setGender("M");
        employeePayroll.setSalary(321000);
        employeePayroll.setStart(LocalDateTime.now());

        when(payrollRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> payrollServices.updatePayroll(id, payrollDto));
    }

}