package com.takbank.tak_wallet.mapper;

import com.takbank.tak_wallet.dto.AccountDto;
import com.takbank.tak_wallet.entity.Account;

public class AccountMapper {

    public static AccountDto mapToDto(Account account, AccountDto accountDto) {
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setCustomerId(account.getCustomerId());
        accountDto.setBalance(account.getBalance());
        accountDto.setCurrency(account.getCurrency());
        return accountDto;
    }

    public static Account mapToEntity(AccountDto accountDto, Account account) {
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setCustomerId(accountDto.getCustomerId());
        account.setBalance(accountDto.getBalance());
        account.setCurrency(accountDto.getCurrency());
        return account;
    }
}