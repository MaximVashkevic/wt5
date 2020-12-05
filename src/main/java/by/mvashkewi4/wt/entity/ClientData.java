package by.mvashkewi4.wt.entity;

import java.io.Serializable;
import java.util.Objects;

public class ClientData implements Serializable {
    private int id;
    private int clientId;
    private int passportId;
    private int addressId;
    private String codeWord;
    private String email;

    public ClientData(String codeWord, String email) {
        this.codeWord = codeWord;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getPassportId() {
        return passportId;
    }

    public void setPassportId(int passportId) {
        this.passportId = passportId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCodeWord() {
        return codeWord;
    }

    public void setCodeWord(String codeWord) {
        this.codeWord = codeWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "id=" + id +
                ", client_id=" + clientId +
                ", passport_id=" + passportId +
                ", address_id=" + addressId +
                ", code_word='" + codeWord + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientData that = (ClientData) o;
        return id == that.id &&
                clientId == that.clientId &&
                passportId == that.passportId &&
                addressId == that.addressId &&
                codeWord.equals(that.codeWord) &&
                email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, passportId, addressId, codeWord, email);
    }
}
