package by.mvashkewi4.wt.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Transaction implements Serializable {
    private int id;
    private int cardId;
    private BigDecimal amount;

    public Transaction(int id, int cardId, BigDecimal amount) {
        this.id = id;
        this.cardId = cardId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", card_id=" + cardId +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id &&
                cardId == that.cardId &&
                amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardId, amount);
    }
}
