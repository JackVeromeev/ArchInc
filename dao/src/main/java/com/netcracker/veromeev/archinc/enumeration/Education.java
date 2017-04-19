package com.netcracker.veromeev.archinc.enumeration;

import com.sun.istack.internal.Nullable;

/**
 * Created by jack on 19/04/17.
 *
 * @author Jack Veromeyev
 */
public enum Education {
    BASIC,
    AVERAGE,
    SPECIAL_AVERAGE,
    PROFESSIONAL_TECH,
    HIGH;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public static Education getById(int id) {
        if (id == BASIC.getId()) {
            return BASIC;
        }
        if (id == AVERAGE.getId()) {
            return AVERAGE;
        }
        if (id == SPECIAL_AVERAGE.getId()) {
            return SPECIAL_AVERAGE;
        }
        if (id == PROFESSIONAL_TECH.getId()) {
            return PROFESSIONAL_TECH;
        }
        if (id == HIGH.getId()) {
            return HIGH;
        }
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Education{");
        String edu;
        switch (this) {
            case BASIC:
                edu = "Basic";
                break;
            case AVERAGE:
                edu = "Average";
                break;
            case SPECIAL_AVERAGE:
                edu = "Special average";
                break;
            case PROFESSIONAL_TECH:
                edu = "Professional technique";
                break;
            case HIGH:
                edu = "High";
                break;
            default:
                edu = "N/A";
        }
        sb.append(edu);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}