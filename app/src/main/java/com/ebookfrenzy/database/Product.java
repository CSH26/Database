package com.ebookfrenzy.database;

/**
 * Created by csh on 2016-03-31.
 */
public class Product {
    private int _id;
    private int _quantity;
    private String _productname;

    public Product(){

    }

    public Product(int id, String productname, int quantity){
        this._id = id;
        this._productname = productname;
        this._quantity = quantity;
    }

    public Product(String productname, int quantity){
        this._productname = productname;
        this._quantity = quantity;
    }

    public void setID(int id){
        this._id = id;
    }

    public void setProductName(String productName){
        this._productname = productName;
    }

    public void setQuantity(int quantity){
        this._quantity = quantity;
    }

    public int getID(){
        return this._id;
    }

    public String getProductName(){
        return this._productname;
    }

    public int getQuantity(){
        return this._quantity;
    }
}
