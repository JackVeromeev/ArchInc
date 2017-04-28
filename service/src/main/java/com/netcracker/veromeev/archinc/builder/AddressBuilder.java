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

    /**
     * constructor for id of Address entity in DB
     * @param id id of Address in DB
     * @throws BuilderException if address with this id doesn/t exist
     */
    public AddressBuilder(int id) throws BuilderException {
        if (id == 0) {
            this.address = new Address(0, 0, 0, 0, "", 0);
        } else {
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
    }

    public AddressBuilder country() throws BuilderException {
        if (this.address.getCountryId() == 0) {
            this.country = new Country(0, "");
        } else {
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
        }
        return this;
    }

    public AddressBuilder region() throws BuilderException {
        if (this.address.getRegionId() == 0) {
            this.region = new Region(0, "");
        } else {
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
        }return this;
    }

    public AddressBuilder town() throws BuilderException {
        if (this.address.getTownId() == 0) {
            this.town = new Town(0, "");
        } else {

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
        }
        return this;
    }

    public  AddressBuilder init() throws BuilderException {
        return this.country().region().town();
    }

    public String getStreet() {
        return this.address.getStreet();
    }

    public void setStreet(String street) {
        this.address.setStreet(street);
    }

    public int getHouse() {
        return this.address.getHouse();
    }

    public void setHouse(int house) {
        this.address.setHouse(house);
    }

    public String getCountry() {
        return country.getCountry();
    }

    public void setCountry(String country) {
        this.country.setCountry(country);
    }

    public String getRegion() {
        return region.getRegion();
    }

    public void setRegion(String region) {
        this.region.setRegion(region);
    }

    public String getTown() {
        return town.getTown();
    }

    public void setTown(String town) {
        this.town.setTown(town);
    }

    @Override
    public void applyChangesToDB() throws BuilderException {
        checkoutCountry();
        checkoutRegion();
        checkoutTown();
        this.address.setCountryId(this.country.getId());
        this.address.setRegionId(this.region.getId());
        this.address.setTownId(this.town.getId());
        if (this.address.getId() != 0) {
            deleteAddress(this.address.getId());
        }
        try {
            this.address = findAddress(this.address);
        } catch (BuilderException e) {
            insertAddress(this.address);
            this.address = findAddress(this.address);
        }

    }

    /**
     * restores id for country name in country field of this class
     * @throws BuilderException if internal error occurred
     */
    private void checkoutCountry() throws BuilderException {
        if (this.country == null) {
            return;
        }
        try {
            // try to find this country in DB
            this.country = getCountryByName(this.country.getCountry());
        } catch (BuilderException e) {
            deleteCountry(this.country.getId());
            insertCountry(this.country);
            this.country = getCountryByName(this.country.getCountry());
        }
    }

    private Country getCountryByName(String name) throws BuilderException {
        List<Country> countries = new LinkedList<>();
        runReadingTransaction("AddressBuilder.getCountryByName(" +
                        name + ")",
                connection -> countries.add(CountryDAO.getInstance().
                        findByName(name, connection))
        );
        return countries.get(0);
    }

    private void insertCountry(Country country) throws BuilderException {
        runAtomicTransaction("AddressBuilder.insertCountry(" +
                        country.getCountry() + ")",
                connection ->
                        CountryDAO.getInstance().insert(country,connection)
        );
    }

    private void deleteCountry(int id) {
        try {
            runAtomicTransaction("AddressBuilder.deleteCountry(id=" +
                            id + ")",
                    connection ->
                            CountryDAO.getInstance().deleteById(id, connection)
            );
        } catch (BuilderException e) {
        }
    }

    /**
     * restores id for region name in region field of this class
     * @throws BuilderException if internal error occurred
     */
    private void checkoutRegion() throws BuilderException {
        if (this.region == null) {
            return;
        }
        try {
            // try to find this region in DB
            this.region = getRegionByName(this.region.getRegion());
        } catch (BuilderException e) {
            deleteRegion(this.region.getId());
            insertRegion(this.region);
            this.region = getRegionByName(this.region.getRegion());
        }
    }

    private Region getRegionByName(String name) throws BuilderException {
        List<Region> regions = new LinkedList<>();
        runReadingTransaction("AddressBuilder.getRegionByName(" +
                        name + ")",
                connection -> regions.add(RegionDAO.getInstance().
                        findByName(name, connection))
        );
        return regions.get(0);
    }

    private void insertRegion(Region region) throws BuilderException {
        runAtomicTransaction("AddressBuilder.insertRegion(" +
                        region.getRegion() + ")",
                connection ->
                        RegionDAO.getInstance().insert(region,connection)
        );
    }

    private void deleteRegion(int id) {
        try {
            runAtomicTransaction("AddressBuilder.deleteRegion(id=" +
                            id + ")",
                    connection ->
                            RegionDAO.getInstance().deleteById(id, connection)
            );
        } catch (BuilderException e) {
        }
    }

    /**
     * restores id for town name in town field of this class
     * @throws BuilderException if internal error occurred
     */
    private void checkoutTown() throws BuilderException {
        if (this.town == null) {
            return;
        }
        try {
            // try to find this town in DB
            this.town = getTownByName(this.town.getTown());
        } catch (BuilderException e) {
            deleteTown(this.town.getId());
            insertTown(this.town);
            this.town = getTownByName(this.town.getTown());
        }
    }

    private Town getTownByName(String name) throws BuilderException {
        List<Town> towns = new LinkedList<>();
        runReadingTransaction("AddressBuilder.getTownByName(" +
                        name + ")",
                connection -> towns.add(TownDAO.getInstance().
                        findByName(name, connection))
        );
        return towns.get(0);
    }

    private void insertTown(Town town) throws BuilderException {
        runAtomicTransaction("AddressBuilder.insertTown(" +
                        town.getTown() + ")",
                connection ->
                        TownDAO.getInstance().insert(town,connection)
        );
    }

    private void deleteTown(int id) {
        try {
            runAtomicTransaction("AddressBuilder.deleteTown(id=" +
                            id + ")",
                    connection ->
                            TownDAO.getInstance().deleteById(id, connection)
            );
        } catch (BuilderException e) {
        }
    }

    private void deleteAddress(int id) {
        try {
            runAtomicTransaction("AddressBuilder.deleteAddress(id=" +
                            id + ")",
                    connection ->
                            AddressDAO.getInstance().deleteById(id, connection)
            );
        } catch (BuilderException e) {
        }
    }

    private void insertAddress(Address address) throws BuilderException {
        runAtomicTransaction("AddressBuilder.insertAddress(" +
                        address.getStreet() + " " + address.getHouse() + ")",
                connection ->
                        AddressDAO.getInstance().insert(address, connection)
        );
    }

    private Address findAddress(Address sourceAddress)
            throws BuilderException {
        List<Address> addresses = new LinkedList<>();
        runReadingTransaction("AddressBuilder.findAddress(" +
                address.getStreet() + " " + address.getHouse() + ")",
                connection -> {
                    List<Address> addressList =
                            AddressDAO.getInstance().readAll(connection);
                    for (Address address : addressList) {
                        if (address.equalsBesideId(sourceAddress)) {
                            addresses.add(address);
                        }
                    }
        });
        if (addresses.size() == 0) {
            throw new BuilderException("No such address");
        }
        return addresses.get(0);
    }
}