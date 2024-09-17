package net.javaguides.banking.service;

import net.javaguides.banking.dto.AccountDto;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long Id);
    AccountDto deposit(Long id,double amount);
    AccountDto withdraw(Long id,double amount);
}
