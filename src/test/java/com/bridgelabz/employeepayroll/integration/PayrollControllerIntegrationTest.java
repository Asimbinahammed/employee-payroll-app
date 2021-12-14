package com.bridgelabz.employeepayroll.integration;

import com.bridgelabz.employeepayroll.controller.PayrollController;
import com.bridgelabz.employeepayroll.dto.PayrollDto;
import com.bridgelabz.employeepayroll.entity.EmployeePayroll;
import com.bridgelabz.employeepayroll.repository.PayrollRepository;
import com.bridgelabz.employeepayroll.services.PayrollServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(PayrollController.class)
public class PayrollControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PayrollServices payrollService;

    @Test
    void getAllPayrollTest() throws Exception {
        when(payrollService.getAllPayroll()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/payroll/employee"))
                .andExpect(status().isOk());
    }

    @Test
    void addPayrollTest() throws Exception {
        when(payrollService.addPayroll(any())).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/payroll/employee")
                .content("{\"name\":\"Manu\",\"gender\":\"M\",\"salary\":\"120000\",\"imagePath\":\"asdf.png\"," +
                        "\"department\":\"CS\",\"notes\":\"HardWorking\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updatePayroll() throws Exception {
        PayrollDto payrollDto = new PayrollDto();
        int id = 1;
        payrollDto.setName("Manu");
        payrollDto.setGender("M");
        payrollDto.setSalary(120000);
        payrollDto.setImagePath("./pic.jpg");
        payrollDto.setDepartment("CS");
        payrollDto.setNotes("HardWorking");
        when(payrollService.updatePayroll(id,payrollDto)).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                .put("/payroll/employee/1")
                .content("{\"name\":\"Manu\",\"gender\":\"M\",\"salary\":\"120000\",\"imagePath\":\"pic.jpg\"," +
                        "\"department\":\"CS\",\"notes\":\"HardWorking\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deletePayroll() throws Exception {
        int id = 1;
        when(payrollService.deletePayroll(id)).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/payroll/employee/1"))
                .andExpect(status().isOk());
    }
}
