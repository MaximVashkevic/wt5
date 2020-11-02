package by.mvashkewi4.wt.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class PassportData implements Serializable {
    private int id;
    private String series;
    private int number;
    private String personalNumber;
    private Date issueDate;
    private Date validityDate;
    private Date birthDate;
    private Sex sex;
    private String authority;
    private String name;
    private String surname;
    private String middlename;
    private String nameEng;
    private String surnameEng;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getSurnameEng() {
        return surnameEng;
    }

    public void setSurnameEng(String surnameEng) {
        this.surnameEng = surnameEng;
    }

    @Override
    public String toString() {
        return "PassportData{" +
                "id=" + id +
                ", series='" + series + '\'' +
                ", number=" + number +
                ", personal_number='" + personalNumber + '\'' +
                ", issue_date=" + issueDate +
                ", validity_date=" + validityDate +
                ", birth_date=" + birthDate +
                ", sex=" + sex +
                ", authority='" + authority + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", name_eng='" + nameEng + '\'' +
                ", surname_eng='" + surnameEng + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassportData that = (PassportData) o;
        return id == that.id &&
                number == that.number &&
                series.equals(that.series) &&
                personalNumber.equals(that.personalNumber) &&
                issueDate.equals(that.issueDate) &&
                validityDate.equals(that.validityDate) &&
                birthDate.equals(that.birthDate) &&
                sex == that.sex &&
                authority.equals(that.authority) &&
                name.equals(that.name) &&
                surname.equals(that.surname) &&
                middlename.equals(that.middlename) &&
                nameEng.equals(that.nameEng) &&
                surnameEng.equals(that.surnameEng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, series, number, personalNumber, issueDate, validityDate, birthDate, sex, authority, name, surname, middlename, nameEng, surnameEng);
    }
}
