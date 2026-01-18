package com.takbank.tak_wallet.service.impl;

import com.takbank.tak_wallet.dto.AccountDto;
import com.takbank.tak_wallet.entity.Account;
import com.takbank.tak_wallet.exception.CustomerAlreadyExistsException;
import com.takbank.tak_wallet.exception.ResourceNotFoundException;
import com.takbank.tak_wallet.mapper.AccountMapper;
import com.takbank.tak_wallet.repository.AccountRepository;
import com.takbank.tak_wallet.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    AccountRepository accountRepository;

    /**
     * @param accountDto - AccountsDto obj
     */
    @Override
    public void createAccount(AccountDto accountDto) {
        Optional<Account> optionalAccount = accountRepository.findByCustomerId(accountDto.getCustomerId());
        if (optionalAccount.isPresent()) {
            throw new CustomerAlreadyExistsException(
                    "Account already registered with given customerId "+ accountDto.getCustomerId()
            );
        }
        accountRepository.save(createNewAccount(accountDto));
    }

    private Account createNewAccount(AccountDto accountDto) {
        Account account = new Account();
        long randomlyAccountNumber = 10000000000L + new Random().nextInt(900000000);
        account.setCurrency(accountDto.getCurrency());
        account.setAccountNumber(String.valueOf(randomlyAccountNumber));
        account.setCustomerId(accountDto.getCustomerId());
        account.setBalance(accountDto.getBalance());
        return account;
    }

    /**
     * @param customerId
     * @return accountDto
     */
    @Cacheable(value = "accounts", key = "#customerId")
    @Override
    public AccountDto fetchAccount(Long customerId) {
        Account account = accountRepository.findByCustomerId(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Account","customerId",customerId.toString()));
        return AccountMapper.mapToDto(account, new AccountDto());
    }

}
