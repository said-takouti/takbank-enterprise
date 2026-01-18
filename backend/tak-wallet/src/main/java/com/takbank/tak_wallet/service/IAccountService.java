package com.takbank.tak_wallet.service;

import com.takbank.tak_wallet.dto.AccountDto;

public interface IAccountService {

    void createAccount(AccountDto accountDto);
    AccountDto fetchAccount(Long customerId);
}
