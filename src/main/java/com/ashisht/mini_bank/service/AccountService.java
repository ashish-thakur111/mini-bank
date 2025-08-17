package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.web.request.AccountDTO;
import com.ashisht.mini_bank.web.response.AccountResponseDTO;

public interface AccountService {
    /**
     * Creates a new account for the given document number.
     *
     * @param accountDTO The DTO containing the document number for the account to be created.
     *                   The document number must be unique.
     *                   If the document number already exists, an exception will be thrown.
     * @return An AccountResponseDTO containing the details of the created account.
     */
    AccountResponseDTO createAccount(AccountDTO accountDTO);

    AccountResponseDTO getAccountById(Long accountId);
}
