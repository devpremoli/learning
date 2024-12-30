package com.example.bankingapp.service;

import com.example.bankingapp.dto.AccountRequestDto;
import com.example.bankingapp.dto.AccountResponseDto;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public List<AccountResponseDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(accountMapper::mapToAccountResponseDto)
                .collect(Collectors.toList());
    }

    public AccountResponseDto createAccount(AccountRequestDto accountRequestDto) {
        Account account = new Account();
        account.setAccountHolderName(accountRequestDto.getAccountName());
        account.setAccountNumber(accountRequestDto.getAccountNumber());
        account.setBalance(accountRequestDto.getInitialDeposit());
        Account savedAccount = accountRepository.save(account);
        return accountMapper.mapToAccountResponseDto(savedAccount);
    }

    public Optional<AccountResponseDto> getAccount(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::mapToAccountResponseDto);
    }

    public Optional<AccountResponseDto> deposit(Long id, double amount) {
        return accountRepository.findById(id).map(account -> {
            account.setBalance(account.getBalance() + amount);
            Account updatedAccount = accountRepository.save(account);
            return accountMapper.mapToAccountResponseDto(updatedAccount);
        });
    }

    public Optional<AccountResponseDto> withdraw(Long id, double amount) {
        return accountRepository.findById(id).map(account -> {
            if (account.getBalance() < amount) {
                throw new RuntimeException("Insufficient funds");
            }
            account.setBalance(account.getBalance() - amount);
            Account updatedAccount = accountRepository.save(account);
            return accountMapper.mapToAccountResponseDto(updatedAccount);
        });
    }
}
