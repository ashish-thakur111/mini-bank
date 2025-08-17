package com.ashisht.mini_bank.mapper;

import com.ashisht.mini_bank.entity.Account;
import com.ashisht.mini_bank.web.request.AccountDTO;
import com.ashisht.mini_bank.web.response.AccountResponseDTO;

/**
 * Mapper class for converting Account entities to AccountResponseDTOs and vice versa.
 * <p>
 * Provides static methods to map domain entities to API response DTOs for accounts.
 */

public class AccountMapper {
    /**
     * Converts an Account entity to an AccountResponseDTO.
     *
     * @param account the Account entity to convert
     * @return an AccountResponseDTO containing account details
     */
    public static AccountResponseDTO toAccountResponseDTO(Account account) {
        if (account == null) {
            return null;
        }
        return AccountResponseDTO.builder()
            .documentNumber(account.getDocumentNumber())
            .id(account.getAccountId())
            .build();
    }

    /**
     * Converts an AccountDTO to an Account entity.
     *
     * @param accountDTO the AccountDTO to convert
     * @return an Account entity with the document number set
     */
    public static Account toAccountEntity(AccountDTO accountDTO) {
        if (accountDTO == null) {
            return null;
        }
        Account account = new Account();
        account.setDocumentNumber(accountDTO.getDocumentNumber());
        return account;
    }
}
