package by.mvashkewi4.wt.entity;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {
    private int id;
    private String country;
    private String region;
    private String district;
    private String settlementType;
    private String settlementName;
    private String streetType;
    private String streetName;
    private short house;
    private Byte building;
    private Integer flat;
    private String zipcode;
    private String phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public String getSettlementName() {
        return settlementName;
    }

    public void setSettlementName(String settlementName) {
        this.settlementName = settlementName;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public short getHouse() {
        return house;
    }

    public void setHouse(short house) {
        this.house = house;
    }

    public Byte getBuilding() {
        return building;
    }

    public void setBuilding(Byte building) {
        this.building = building;
    }

    public Integer getFlat() {
        return flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", settlement_type='" + settlementType + '\'' +
                ", settlement_name='" + settlementName + '\'' +
                ", street_type='" + streetType + '\'' +
                ", street_name='" + streetName + '\'' +
                ", house=" + house +
                ", building=" + building +
                ", flat=" + flat +
                ", zipcode='" + zipcode + '\'' +
                ", phone_number='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id &&
                house == address.house &&
                country.equals(address.country) &&
                region.equals(address.region) &&
                district.equals(address.district) &&
                settlementType.equals(address.settlementType) &&
                settlementName.equals(address.settlementName) &&
                streetType.equals(address.streetType) &&
                streetName.equals(address.streetName) &&
                Objects.equals(building, address.building) &&
                Objects.equals(flat, address.flat) &&
                zipcode.equals(address.zipcode) &&
                Objects.equals(phoneNumber, address.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, region, district, settlementType, settlementName, streetType, streetName, house, building, flat, zipcode, phoneNumber);
    }
}
