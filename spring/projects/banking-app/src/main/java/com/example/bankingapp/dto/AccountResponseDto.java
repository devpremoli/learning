package com.example.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountResponseDto {
    private Long accountId;
    private String accountName;
    private String accountNumber;
    private Double balance;
}
