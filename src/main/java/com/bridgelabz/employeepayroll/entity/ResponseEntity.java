package com.bridgelabz.employeepayroll.entity;

import lombok.Data;

@Data
public class ResponseEntity {
    private String message;
    private Object data;
    private Object status;

    public ResponseEntity(String message,Object data, Object status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }
}
