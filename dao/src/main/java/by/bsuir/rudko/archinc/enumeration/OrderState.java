package by.bsuir.rudko.archinc.enumeration;

public enum OrderState {
    NEW, READY, PAYED;

    public static OrderState of(String orderState) {
        switch (orderState) {
            case "N":
                return OrderState.NEW;
            case "R":
                return OrderState.READY;
            case "P":
                return OrderState.PAYED;
        }
        return null;
    }

    public String toSQLString() {
        if  (this == NEW) {
            return "N";
        } else if (this == READY) {
            return "R";
        } else if (this == PAYED) {
            return "P";
        }
        return "";
    }
}
