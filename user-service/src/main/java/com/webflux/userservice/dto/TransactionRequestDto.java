package com.webflux.userservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionRequestDto {
    private Integer userID;
    private Integer amount;
}
