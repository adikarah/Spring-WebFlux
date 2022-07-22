package com.webflux.userservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionResponseDto {
    private Integer userID;
    private Integer amount;
    private TransactionStatus transactionStatus;
}
