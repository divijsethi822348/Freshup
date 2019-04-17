package com.example.freshup.Util;

import com.example.freshup.Models.SingleProductCategoryModel;

import java.util.Date;
import java.util.List;

public class Singleton {
    String subSubProductId;
    List<SingleProductCategoryModel.Product> subSubProducts;
    String service_id;
    String product_id;
    String appointmentDate;
    String productId;

    public List<SingleProductCategoryModel.Product> getSubSubProducts() {
        return subSubProducts;
    }

    public void setSubSubProducts(List<SingleProductCategoryModel.Product> subSubProducts) {
        this.subSubProducts = subSubProducts;
    }

    public String getSubSubProductId() {
        return subSubProductId;
    }

    public void setSubSubProductId(String subSubProductId) {
        this.subSubProductId = subSubProductId;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
