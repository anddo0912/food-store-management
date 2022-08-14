/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

/**
 *
 * @author LamHung
 */
public class OrderDetailDTO {
    private int detailID;
    private double price;
    private int quantity;
    private int orderID;
    private String productID;
    private String status;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(double price, int quantity, int orderID, String productID, String status) {
        this.price = price;
        this.quantity = quantity;
        this.orderID = orderID;
        this.productID = productID;
        this.status = status;
    }

    public OrderDetailDTO(int detailID, double price, int quantity, int orderID, String productID, String status) {
        this.detailID = detailID;
        this.price = price;
        this.quantity = quantity;
        this.orderID = orderID;
        this.productID = productID;
        this.status = status;
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
