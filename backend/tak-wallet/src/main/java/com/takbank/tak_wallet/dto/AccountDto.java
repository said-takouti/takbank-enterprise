package com.takbank.tak_wallet.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class AccountDto implements Serializable {

    private String accountNumber;
    private Long customerId;
    private BigDecimal balance;
    private String currency;

}