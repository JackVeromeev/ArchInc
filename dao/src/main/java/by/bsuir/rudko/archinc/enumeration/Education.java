package by.bsuir.rudko.archinc.enumeration;

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
        switch (this) {
            case BASIC:
                return "Basic";
            case AVERAGE:
                return "Average";
            case SPECIAL_AVERAGE:
                return "Special average";
            case PROFESSIONAL_TECH:
                return "Professional technical";
            case HIGH:
                return "High";
        }
        return "N/A";
    }
}