/**
 * 
 */
package com.revolut.transaction.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Elangovan
 *
 */
public class TransactionDetails {

    @JsonProperty(required = true)
    private String currencyCode;

    @JsonProperty(required = true)
    private BigDecimal amount;

    @JsonProperty(required = true)
    private Long fromAccountId;

    @JsonProperty(required = true)
    private Long toAccountId;

    public TransactionDetails() {
    }

    public TransactionDetails(String currencyCode, BigDecimal amount, Long fromAccountId, Long toAccountId) {
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TransactionDetails transaction = (TransactionDetails) o;

        if (!currencyCode.equals(transaction.currencyCode))
            return false;
        if (!amount.equals(transaction.amount))
            return false;
        if (!fromAccountId.equals(transaction.fromAccountId))
            return false;
        return toAccountId.equals(transaction.toAccountId);

    }

    @Override
    public int hashCode() {
        int result = currencyCode.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + fromAccountId.hashCode();
        result = 31 * result + toAccountId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserTransaction{" + "currencyCode='" + currencyCode + '\'' + ", amount=" + amount + ", fromAccountId="
                + fromAccountId + ", toAccountId=" + toAccountId + '}';
    }

}
