package com.example.adminpc.deliveryexecutiveapp;

/**
 * Created by adminpc on 20-06-2019.
 */

public class OrdersPOJO {
    private String orderId;
    private String userId;
    private String address;
    private String phoneNo;
    private String restaurantId;
    private int totalItems;

    public OrdersPOJO(String orderId, String userId, String address, String phoneNo, String restaurantId, int totalItems) {
        this.orderId = orderId;
        this.userId = userId;
        this.address = address;
        this.phoneNo = phoneNo;
        this.restaurantId = restaurantId;
        this.totalItems = totalItems;

    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
