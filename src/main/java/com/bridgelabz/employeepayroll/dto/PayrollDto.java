package com.bridgelabz.employeepayroll.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Purpose : To declare input and their regex for validations.
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-03
 */
@Data
public class PayrollDto {
    @NotNull
    @Size(min = 3, message = "Name should have atleast 3 characters")
    @Pattern(regexp = "[a-zA-Z]+[\\s]?[a-zA-Z ]+$",
            message = "Name Only contains alphabets")
    private String name;

    @NotNull
    @Size(min = 1,max = 1, message = "Name should have only letters")
    @Pattern(regexp = "[Male|Female]$",
            message = "Name Only contain gender alphabet")
    private String gender;

    @NotNull
    private long salary;
    @NotNull
    private String imagePath;
    @NotNull
    private List<String> department;
    @NotNull
    private String notes;


}

