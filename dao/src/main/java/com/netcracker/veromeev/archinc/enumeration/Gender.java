package com.netcracker.veromeev.archinc.enumeration;

/**
 * Created by jack on 12/04/17.
 *
 * @author Jack Veromeyev
 */
public enum Gender {
    MALE,
    FEMALE;

    public static Gender of(String gender) {
        switch (gender) {
            case "M": return MALE;
            case "F": return FEMALE;
            default: throw new IllegalArgumentException("\"" + gender
                    + "\" is not legal for gender (Are you transgender?)");
        }
    }
}
