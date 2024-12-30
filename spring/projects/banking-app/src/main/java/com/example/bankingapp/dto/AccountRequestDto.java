package com.example.bankingapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequestDto {
    private String accountName;
    private String accountNumber;
    private Double initialDeposit;

}
