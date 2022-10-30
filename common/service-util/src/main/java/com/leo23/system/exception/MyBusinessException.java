package com.leo23.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyBusinessException extends RuntimeException {
    private Integer code;
    private String message;

}