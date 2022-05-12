package com.tutorial.Springboot.database;

import com.tutorial.Springboot.model.Product;

import java.sql.PreparedStatement;

public class ProductDAO extends DAO{
    public void addProduct(Product product){
        String sqlInsert = "insert into test.product (productName,year,price,url) value (?,?,?,?);";
        try ( PreparedStatement pr = con.prepareStatement(sqlInsert)) {
           // pr.setLong(1, product.getId());
            pr.setString(1, product.getProductName());
            pr.setInt(2,product.getYear());
            pr.setDouble(3,product.getPrice());
            pr.setString(4, product.getUrl());
            System.out.println("ProductDao: " + product.getId());
            //pr.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sqlInsert);
    }
}
