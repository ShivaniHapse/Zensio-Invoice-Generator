package com.zensio;

import lombok.Data;

@Data
public class CoworkingFacility {
    private int quantity;
    private int price;
    private int GST;
    private int totalGST;
    private int totalPrice;

    public CoworkingFacility(int quantity, int price, int GST) {
        this.quantity = quantity;
        this.price = price;
        this.GST = GST;
        this.totalGST = price * quantity * GST / 100;
        this.totalPrice = price * quantity + totalGST;
    }
}