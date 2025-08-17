package com.ashisht.mini_bank.mapper;

import com.ashisht.mini_bank.entity.Account;
import com.ashisht.mini_bank.web.request.AccountDTO;
import com.ashisht.mini_bank.web.response.AccountResponseDTO;

public class AccountMapper {
    public static AccountResponseDTO toAccountResponseDTO(Account account) {
        if (account == null) {
            return null;
        }
        return AccountResponseDTO.builder()
            .documentNumber(account.getDocumentNumber())
            .id(account.getAccountId())
            .build();
    }

    public static Account toAccountEntity(AccountDTO accountDTO) {
        if (accountDTO == null) {
            return null;
        }
        Account account = new Account();
        account.setDocumentNumber(accountDTO.getDocumentNumber());
        return account;
    }
}
