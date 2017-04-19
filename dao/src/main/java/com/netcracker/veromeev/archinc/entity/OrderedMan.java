package com.netcracker.veromeev.archinc.entity;

import java.util.Objects;

/**
 * Created by jack on 17/04/17.
 *
 * @author Jack Veromeyev
 */
public class OrderedMan extends Entity {

    private int orderId;
    private int qualificationId;
    private int amount;

    public OrderedMan(int id, int orderId, int qualificationId, int amount) {
        super(id);
        this.orderId = orderId;
        this.qualificationId = qualificationId;
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(int qualificationId) {
        this.qualificationId = qualificationId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderedMan)) return false;
        if (!super.equals(o)) return false;
        OrderedMan that = (OrderedMan) o;
        return orderId == that.orderId &&
                qualificationId == that.qualificationId &&
                amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId, qualificationId, amount);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderedMan{");
        sb.append("id=").append(getId());
        sb.append(", orderId=").append(orderId);
        sb.append(", qualificationId=").append(qualificationId);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }


}
