package by.mvashkewi4.wt.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Account implements Serializable {
    private int id;
    private int userId;
    private int currencyId;
    private String accountNumber;
    private Boolean isLocked;
    private BigDecimal amount;

    public Account(int id, int userId, int currencyId, String accountNumber, Boolean isLocked, BigDecimal amount) {
        this.id = id;
        this.userId = userId;
        this.currencyId = currencyId;
        this.accountNumber = accountNumber;
        this.isLocked = isLocked;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", user_id=" + userId +
                ", currency_id=" + currencyId +
                ", account_number='" + accountNumber + '\'' +
                ", is_locked=" + isLocked +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                userId == account.userId &&
                currencyId == account.currencyId &&
                accountNumber.equals(account.accountNumber) &&
                isLocked.equals(account.isLocked) &&
                amount.equals(account.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, currencyId, accountNumber, isLocked, amount);
    }
}
