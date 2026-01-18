package com.takbank.tak_wallet.controller;


import com.takbank.tak_wallet.dto.AccountDto;
import com.takbank.tak_wallet.dto.ResponseDto;
import com.takbank.tak_wallet.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = "/api/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountController {

    private IAccountService iAccountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody AccountDto accountDto) {
        iAccountService.createAccount(accountDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Account created successfully"));
    }

    @GetMapping("/fetch")
    public ResponseEntity<AccountDto> fetchAccount(@RequestParam Long customerId){
        AccountDto accountDto = iAccountService.fetchAccount(customerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountDto);
    }
}