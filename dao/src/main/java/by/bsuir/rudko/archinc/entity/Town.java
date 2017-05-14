package by.bsuir.rudko.archinc.entity;

import java.util.Objects;

/**
 * Created by jack on 19/04/17.
 *
 * @author Jack Veromeyev
 */
public class Town extends Entity {

    private String town;

    public Town(int id, String town) {
        super(id);
        this.setTown(town);
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Town)) return false;
        if (!super.equals(o)) return false;
        Town town1 = (Town) o;
        return Objects.equals(town, town1.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), town);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Town{");
        sb.append("id=").append(getId());
        sb.append(", town='").append(town).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
