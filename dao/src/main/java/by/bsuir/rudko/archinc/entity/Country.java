package by.bsuir.rudko.archinc.entity;

import java.util.Objects;

/**
 * Created by jack on 19/04/17.
 *
 * @author Jack Veromeyev
 */
public class Country extends Entity {

    private String country;

    public Country(int id, String country) {
        super(id);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        if (!super.equals(o)) return false;
        Country that = (Country) o;
        return Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), country);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Country{");
        sb.append("id=").append(getId());
        sb.append(", country='").append(country).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
