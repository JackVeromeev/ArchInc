package com.netcracker.veromeev.archinc.builder;

import com.netcracker.veromeev.archinc.dao.AddressDAO;
import com.netcracker.veromeev.archinc.dao.CountryDAO;
import com.netcracker.veromeev.archinc.dao.RegionDAO;
import com.netcracker.veromeev.archinc.dao.TownDAO;
import com.netcracker.veromeev.archinc.entity.Address;
import com.netcracker.veromeev.archinc.entity.Country;
import com.netcracker.veromeev.archinc.entity.Region;
import com.netcracker.veromeev.archinc.entity.Town;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 27/04/17.
 *
 * @author Jack Veromeyev
 */
public class AddressBuilder extends AbstractBuilder {
    private Address address;
    private Country country;
    private Region region;
    private Town town;

    public AddressBuilder(Address address) {
        this.address = address;
        town = null;
        region = null;
        country = null;
    }

    public AddressBuilder(int id) throws BuilderException {
        List<Address> addresses = new LinkedList<>();
        runReadingTransaction("AddressBuilder(id=" + id + ")",
                connection -> {
                    Address address =
                            AddressDAO.getInstance().findById(id, connection);
                    addresses.add(address);
        });
        if (addresses.size() == 0) {
            throw new BuilderException("address id=" + id + " not found");
        }
        this.address = addresses.get(0);
    }

    public AddressBuilder country() throws BuilderException {
        List<Country> countries = new LinkedList<>();
        runReadingTransaction("country(id=" + address.getCountryId() + ")",
                connection -> {
                    Country country = CountryDAO.getInstance()
                            .findById(address.getCountryId(), connection);
                    countries.add(country);
        });
        if (countries.size() == 0) {
            throw new BuilderException("country id=" + address.getCountryId()
                    + " not found");
        }
        this.country = countries.get(0);
        return this;
    }

    public AddressBuilder region() throws BuilderException {
        List<Region> regions = new LinkedList<>();
        runReadingTransaction("region(id=" + address.getRegionId() + ")",
                connection -> {
                    Region region = RegionDAO.getInstance()
                            .findById(address.getRegionId(), connection);
                    regions.add(region);
                });
        if (regions.size() == 0) {
            throw new BuilderException("region id=" + address.getRegionId()
                    + " not found");
        }
        this.region = regions.get(0);
        return this;
    }

    public AddressBuilder town() throws BuilderException {
        List<Town> towns = new LinkedList<>();
        runReadingTransaction("town(id=" + address.getTownId() + ")",
                connection -> {
                    Town town = TownDAO.getInstance()
                            .findById(address.getTownId(), connection);
                    towns.add(town);
                });
        if (towns.size() == 0) {
            throw new BuilderException("town id=" + address.getCountryId()
                    + " not found");
        }
        this.town = towns.get(0);
        return this;
    }

    public  AddressBuilder init() throws BuilderException {
        return this.country().region().town();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    // TODO finish saveChanges;

}
