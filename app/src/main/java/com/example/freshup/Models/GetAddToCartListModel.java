package com.example.freshup.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAddToCartListModel {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;
    @SerializedName("totalPrice")
    @Expose
    private String totalPrice;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }


    public static class Detail {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("userId")
        @Expose
        private String userId;
        @SerializedName("productId")
        @Expose
        private String productId;
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("paymentStatus")
        @Expose
        private String paymentStatus;
        @SerializedName("productSaleStatus")
        @Expose
        private String productSaleStatus;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("updated")
        @Expose
        private String updated;
        @SerializedName("title")
        @Expose
        private Object title;
        @SerializedName("productImage")
        @Expose
        private Object productImage;
        @SerializedName("description")
        @Expose
        private Object description;
        @SerializedName("price")
        @Expose
        private Object price;
        @SerializedName("cartPrice")
        @Expose
        private Integer cartPrice;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public String getProductSaleStatus() {
            return productSaleStatus;
        }

        public void setProductSaleStatus(String productSaleStatus) {
            this.productSaleStatus = productSaleStatus;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public Object getProductImage() {
            return productImage;
        }

        public void setProductImage(Object productImage) {
            this.productImage = productImage;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Object getPrice() {
            return price;
        }

        public void setPrice(Object price) {
            this.price = price;
        }

        public Integer getCartPrice() {
            return cartPrice;
        }

        public void setCartPrice(Integer cartPrice) {
            this.cartPrice = cartPrice;
        }

    }
}
