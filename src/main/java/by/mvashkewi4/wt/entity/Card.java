package by.mvashkewi4.wt.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Card implements Serializable {
    private int id;
    private int accountId;
    private String numberHash;
    private Date expirationDate;
    private short lastDigits;
    private String pinHash;
    private String cvvHash;

    public Card(int id, int accountId, String numberHash, Date expirationDate, short lastDigits, String pinHash, String cvvHash) {
        this.id = id;
        this.accountId = accountId;
        this.numberHash = numberHash;
        this.expirationDate = expirationDate;
        this.lastDigits = lastDigits;
        this.pinHash = pinHash;
        this.cvvHash = cvvHash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getNumberHash() {
        return numberHash;
    }

    public void setNumberHash(String numberHash) {
        this.numberHash = numberHash;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public short getLastDigits() {
        return lastDigits;
    }

    public void setLastDigits(short lastDigits) {
        this.lastDigits = lastDigits;
    }

    public String getPinHash() {
        return pinHash;
    }

    public void setPinHash(String pinHash) {
        this.pinHash = pinHash;
    }

    public String getCvvHash() {
        return cvvHash;
    }

    public void setCvvHash(String cvvHash) {
        this.cvvHash = cvvHash;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", account_id=" + accountId +
                ", number_hash='" + numberHash + '\'' +
                ", expiration_date=" + expirationDate +
                ", last_digits=" + lastDigits +
                ", pin_hash='" + pinHash + '\'' +
                ", cvv_hash='" + cvvHash + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id &&
                accountId == card.accountId &&
                lastDigits == card.lastDigits &&
                numberHash.equals(card.numberHash) &&
                expirationDate.equals(card.expirationDate) &&
                pinHash.equals(card.pinHash) &&
                cvvHash.equals(card.cvvHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, numberHash, expirationDate, lastDigits, pinHash, cvvHash);
    }
}
