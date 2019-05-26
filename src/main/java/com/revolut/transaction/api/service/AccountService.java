package com.revolut.transaction.api.service;

import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revolut.transaction.api.dao.DAOFactory;
import com.revolut.transaction.api.exception.TransactionException;
import com.revolut.transaction.api.model.Account;
import com.revolut.transaction.api.utils.ConverterUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Account Service
 */
@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountService {

    private final com.revolut.transaction.api.dao.DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.H2);

    private static Logger log = Logger.getLogger(AccountService.class);

    /**
     * Find all accounts
     * 
     * @return
     * @throws TransactionException
     */
    @GET
    @Path("/all")
    public List<Account> getAllAccounts() throws TransactionException {
        return daoFactory.getAccountDAO().getAllAccounts();
    }

    /**
     * Find by account id
     * 
     * @param accountId
     * @return
     * @throws TransactionException
     */
    @GET
    @Path("/{accountId}")
    public Account getAccount(@PathParam("accountId") long accountId) throws TransactionException {
        return daoFactory.getAccountDAO().getAccountById(accountId);
    }

    /**
     * Find balance by account Id
     * 
     * @param accountId
     * @return
     * @throws TransactionException
     */
    @GET
    @Path("/{accountId}/balance")
    public BigDecimal getBalance(@PathParam("accountId") long accountId) throws TransactionException {
        final Account account = daoFactory.getAccountDAO().getAccountById(accountId);

        if (account == null) {
            throw new WebApplicationException("Account not found", Response.Status.NOT_FOUND);
        }
        return account.getBalance();
    }

    /**
     * Deposit amount by account Id
     * 
     * @param accountId
     * @param amount
     * @return
     * @throws TransactionException
     */
    @PUT
    @Path("/{accountId}/deposit/{amount}")
    public Account deposit(@PathParam("accountId") long accountId, @PathParam("amount") BigDecimal amount)
            throws TransactionException {

        if (amount.compareTo(ConverterUtil.zeroAmount) <= 0) {
            throw new WebApplicationException("Invalid Deposit amount", Response.Status.BAD_REQUEST);
        }

        daoFactory.getAccountDAO().updateAccountBalance(accountId, amount.setScale(4, RoundingMode.HALF_EVEN));
        return daoFactory.getAccountDAO().getAccountById(accountId);
    }

    /**
     * Withdraw amount by account Id
     * 
     * @param accountId
     * @param amount
     * @return
     * @throws TransactionException
     */
    @PUT
    @Path("/{accountId}/withdraw/{amount}")
    public Account withdraw(@PathParam("accountId") long accountId, @PathParam("amount") BigDecimal amount)
            throws TransactionException {

        if (amount.compareTo(ConverterUtil.zeroAmount) <= 0) {
            throw new WebApplicationException("Invalid Deposit amount", Response.Status.BAD_REQUEST);
        }
        BigDecimal delta = amount.negate();
        if (log.isDebugEnabled())
            log.debug("Withdraw service: delta change to account  " + delta + " Account ID = " + accountId);
        daoFactory.getAccountDAO().updateAccountBalance(accountId, delta.setScale(4, RoundingMode.HALF_EVEN));
        return daoFactory.getAccountDAO().getAccountById(accountId);
    }
}
