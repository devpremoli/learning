package com.example.bankingapp.controller;

import com.example.bankingapp.dto.AccountRequestDto;
import com.example.bankingapp.dto.AccountResponseDto;
import com.example.bankingapp.entity.Account;
import com.example.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountResponseDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping
    public AccountResponseDto createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return accountService.createAccount(accountRequestDto);
    }

    @GetMapping("/{id}")
    public AccountResponseDto getAccount(@PathVariable Long id) {
        return accountService.getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @PostMapping("/{id}/deposit")
    public AccountResponseDto deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return accountService.deposit(id, amount).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @PostMapping("/{id}/withdraw")
    public AccountResponseDto withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return accountService.withdraw(id, amount)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
