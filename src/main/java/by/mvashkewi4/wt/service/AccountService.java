package by.mvashkewi4.wt.service;

import by.mvashkewi4.wt.entity.Account;
import by.mvashkewi4.wt.entity.User;

import java.util.List;

public interface AccountService {
    List<Account> getAccounts(User user);

    void addAccount(Account account);

}
