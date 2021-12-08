package com.bridgelabz.employeepayroll.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * purpose : Defining error details
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-03
 */
@Getter
@Setter
public class ErrorResponse {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
