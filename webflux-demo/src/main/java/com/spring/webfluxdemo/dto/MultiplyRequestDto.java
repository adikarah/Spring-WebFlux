package com.spring.webfluxdemo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MultiplyRequestDto {
    private int firstNumber;
    private int secondNumber;

}
