package com.netcracker.veromeev.archinc.entity;

import java.util.Objects;

/**
 * Created by jack on 19/04/17.
 *
 * @author Jack Veromeyev
 */
public class Region extends Entity {

    private String region;

    public Region(int id, String region) {
        super(id);
        this.setRegion(region);
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Region)) return false;
        if (!super.equals(o)) return false;
        Region that = (Region) o;
        return Objects.equals(region, that.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), region);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Region{");
        sb.append("id=").append(getId());
        sb.append(", region='").append(region).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
