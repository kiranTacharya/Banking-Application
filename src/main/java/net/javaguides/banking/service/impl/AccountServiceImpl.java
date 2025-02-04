package net.javaguides.banking.service.impl;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.mapper.AccountMapper;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    public AccountDto createAccount(AccountDto accountDto){
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount =accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long Id) {
        Account account=accountRepository.findById(Id)
                .orElseThrow(()->new RuntimeException("Account does not exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
         Account account= accountRepository.findById(id)
                 .orElseThrow(()-> new RuntimeException("Account does not exists"));
         double total=account.getBalance()+amount;
         account.setBalance(total);
      Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account= accountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exists"));
        if (account.getBalance()<amount){
            throw new RuntimeException("insufficient balance");
        }
        double total=account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

}
