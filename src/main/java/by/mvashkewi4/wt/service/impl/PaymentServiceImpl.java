package by.mvashkewi4.wt.service.impl;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.dao.DAOProvider;
import by.mvashkewi4.wt.dao.interfaces.AccountDAO;
import by.mvashkewi4.wt.dao.interfaces.CardDAO;
import by.mvashkewi4.wt.dao.interfaces.TransactionDAO;
import by.mvashkewi4.wt.entity.Account;
import by.mvashkewi4.wt.entity.Card;
import by.mvashkewi4.wt.entity.Transaction;
import by.mvashkewi4.wt.entity.User;
import by.mvashkewi4.wt.service.PaymentService;
import by.mvashkewi4.wt.service.ServiceException;
import by.mvashkewi4.wt.service.validation.TransactionValidator;

import java.math.BigDecimal;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ClientServiceImpl.class);

    @Override
    public List<Account> getAccounts(User user) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        AccountDAO accountDAO = daoProvider.getAccountDAO();

        try {
            return accountDAO.getUserAccounts(user.getId());
        } catch (DAOException e) {
            log.error("DAO exception", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void addAccount(Account account) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        AccountDAO accountDAO = daoProvider.getAccountDAO();

        try {
            accountDAO.addAccount(account);
        } catch (DAOException e) {
            log.error("DAO exception", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void lockAccount(Account account) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        AccountDAO accountDAO = daoProvider.getAccountDAO();

        try {
            accountDAO.lockAccount(account);
        } catch (DAOException e) {
            log.error("DAO exception", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void addCard(Card card) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        CardDAO cardDAO = daoProvider.getCardDAO();

        try {
            cardDAO.addCard(card);
        } catch (DAOException e) {
            log.error("DAO exception", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Card> getCards(Account account) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        CardDAO cardDAO = daoProvider.getCardDAO();

        try {
            return cardDAO.getCards(account.getId());
        } catch (DAOException e) {
            log.error("DAO exception", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void transferMoney(Card from, Card to, BigDecimal amount) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        AccountDAO accountDAO = daoProvider.getAccountDAO();
        TransactionDAO transactionDAO = daoProvider.getTransactionDAO();

        try {
            Account fromAccount = accountDAO.getAccount(from.getAccountId());
            if (TransactionValidator.containsEnoughMoney(fromAccount, amount)) {
                Transaction transaction = new Transaction(from.getId(), amount);
                transactionDAO.addTransaction(transaction);

                accountDAO.changeAmount(fromAccount, fromAccount.getAmount().subtract(amount));

                Account toAccount = accountDAO.getAccount(to.getAccountId());
                accountDAO.changeAmount(toAccount, toAccount.getAmount().add(amount));
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
