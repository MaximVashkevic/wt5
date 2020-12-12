package by.mvashkewi4.wt.dao.interfaces;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.entity.Card;

import java.util.List;

public interface CardDAO {
    List<Card> getCards(int accountId) throws DAOException;

    void addCard(Card card) throws DAOException;
}
