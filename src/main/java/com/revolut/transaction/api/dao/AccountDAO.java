/**
 * 
 */
package com.revolut.transaction.api.dao;

import java.math.BigDecimal;
import java.util.List;

import com.revolut.transaction.api.exception.TransactionException;
import com.revolut.transaction.api.model.Account;
import com.revolut.transaction.api.model.TransactionDetails;

/**
 * @author Elangovan
 *
 */
public interface AccountDAO {

	List<Account> getAllAccounts() throws TransactionException;

	Account getAccountById(long accountId) throws TransactionException;

	/**
	 *
	 * @param accountId user accountId
	 * @param amount    amount to be debit(less than 0)/credit(greater than 0).
	 * @return no. of rows updated
	 */
	int updateAccountBalance(long accountId, BigDecimal amount) throws TransactionException;

	int transferAccountBalance(TransactionDetails transactionDetails) throws TransactionException;
}