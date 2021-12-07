package com.bridgelabz.employeepayroll.exception;


/**
 * Purpose : Defining ResourceNotFoundException
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-03
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}