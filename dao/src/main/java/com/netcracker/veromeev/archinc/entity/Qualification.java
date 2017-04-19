package com.netcracker.veromeev.archinc.entity;

import java.util.Objects;

/**
 * Created by jack on 17/04/17.
 *
 * @author Jack Veromeyev
 */
public class Qualification extends Entity {

    private String qualification;

    public Qualification(int id, String qualification) {
        super(id);
        this.qualification = qualification;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Qualification)) return false;
        if (!super.equals(o)) return false;
        Qualification that = (Qualification) o;
        return Objects.equals(qualification, that.qualification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), qualification);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Qualification{");
        sb.append("id=").append(getId());
        sb.append(", qualification='").append(qualification).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
