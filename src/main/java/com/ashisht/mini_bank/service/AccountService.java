package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.entity.Account;

/**
 * Service interface for account-related business logic.
 * <p>
 * Defines contract for creating and retrieving accounts.
 */

public interface AccountService {
    /**
     * Creates a new account for the given document number.
     *
     * @param documentNumber The document number for the account to be created.
     *                      The document number must be unique.
     *                      If the document number already exists, an exception will be thrown.
     * @return The created Account entity.
     */
    Account createAccount(String documentNumber);

    /**
     * Retrieves an account by its ID.
     *
     * @param accountId The ID of the account to retrieve.
     *                  If no account is found with the given ID, an exception will be thrown.
     * @return The Account entity corresponding to the given ID.
     */
    Account getAccountById(Long accountId);
}
