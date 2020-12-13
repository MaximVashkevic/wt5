package by.mvashkewi4.wt.service;

import by.mvashkewi4.wt.entity.Account;
import by.mvashkewi4.wt.entity.Card;
import by.mvashkewi4.wt.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    List<Account> getAccounts(User user) throws ServiceException;

    void addAccount(Account account) throws ServiceException;

    void lockAccount(Account account) throws ServiceException;

    void addCard(Card card) throws ServiceException;

    List<Card> getCards(Account account) throws ServiceException;

    void transferMoney(Card from, Card to, BigDecimal amount) throws ServiceException;
}
