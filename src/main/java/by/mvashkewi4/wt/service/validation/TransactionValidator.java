package by.mvashkewi4.wt.service.validation;

import by.mvashkewi4.wt.entity.Account;

import java.math.BigDecimal;

public class TransactionValidator {
    public static boolean containsEnoughMoney(Account account, BigDecimal amount) {
        return account.getAmount().compareTo(amount) >= 0;
    }
}
