package com.example.bankingapp.service;

import com.example.bankingapp.dto.AccountResponseDto;
import com.example.bankingapp.entity.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    public AccountResponseDto mapToAccountResponseDto(Account account) {
        if (account == null) {
            return null;
        }
        return new AccountResponseDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getAccountNumber(),
                account.getBalance()
        );
    }
}
