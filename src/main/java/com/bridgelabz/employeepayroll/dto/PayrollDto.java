package com.bridgelabz.employeepayroll.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @Size(min = 1,max = 1, message = "Name should have only 1 character")
    @Pattern(regexp = "[F|f|M|m|o|O]$",
            message = "Name Only contain gender alphabet")
    private String gender;

    @NotNull
    private long salary;
//    @Pattern(regexp = "  ([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)",
//            message = "image path  contains file location of image")
    @NotNull
    private String imagePath;
    @Pattern(regexp = "[a-zA-Z]+[\\s]?[a-zA-Z ]+$",
            message = "Department Only contains alphabets")
    @NotNull
    private String department;
    @NotNull
    private String notes;


}

