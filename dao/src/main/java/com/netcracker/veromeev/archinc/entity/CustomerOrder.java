package com.netcracker.veromeev.archinc.entity;

import com.netcracker.veromeev.archinc.enumeration.OrderState;

import java.util.Objects;

public class CustomerOrder extends Entity{

    private String title;
    private String description;
    private int userId;
    private OrderState orderState;

    public CustomerOrder(int id, String title, String description, int userId,
                         OrderState orderState) {
        super(id);
        this.title = title;
        this.description = description;
        this.orderState = orderState;
        this.userId = userId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerOrder)) return false;
        if (!super.equals(o)) return false;
        CustomerOrder customerOrder = (CustomerOrder) o;
        return userId == customerOrder.userId &&
                Objects.equals(title, customerOrder.title) &&
                Objects.equals(description, customerOrder.description) &&
                orderState == customerOrder.orderState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, userId,
                orderState);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomerOrder{");
        sb.append("id=").append(getId());
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", orderState='").append(orderState).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
