package com.bridgelabz.employeepayroll.entity;

import lombok.Data;

/**
 * Purpose : To declare response entity.
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-03
 */
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
