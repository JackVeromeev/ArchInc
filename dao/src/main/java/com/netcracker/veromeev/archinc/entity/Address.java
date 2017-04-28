package com.netcracker.veromeev.archinc.entity;

import java.util.Objects;

/**
 * Created by jack on 19/04/17.
 *
 * @author Jack Veromeyev
 */
public class Address extends Entity {
    private int townId;
    private int countryId;
    private int regionId;
    private String Street;
    private int house;

    public Address(int id, int townId, int countryId, int regionId,
                   String street, int house) {
        super(id);
        this.townId = townId;
        this.countryId = countryId;
        this.regionId = regionId;
        Street = street;
        this.house = house;
    }

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        if (!super.equals(o)) return false;
        Address address = (Address) o;
        return townId == address.townId &&
                countryId == address.countryId &&
                regionId == address.regionId &&
                house == address.house &&
                Objects.equals(Street, address.Street);
    }

    public boolean equalsBesideId(Address address) {
        return this == address
                || townId == address.townId
                && countryId == address.countryId
                && regionId == address.regionId
                && house == address.house
                && Objects.equals(Street, address.Street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), townId, countryId, regionId, Street, house);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("id=").append(getId());
        sb.append(", townId=").append(townId);
        sb.append(", countryId=").append(countryId);
        sb.append(", regionId=").append(regionId);
        sb.append(", Street='").append(Street).append('\'');
        sb.append(", house=").append(house);
        sb.append('}');
        return sb.toString();
    }


}
