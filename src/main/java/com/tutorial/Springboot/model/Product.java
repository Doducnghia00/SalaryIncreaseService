package com.tutorial.Springboot.model;


import javax.persistence.*;
import java.util.Calendar;

//POJO = Plain Object Java Object
@Entity
@Table(name = "product")
public class Product {

    //this is "primary key"
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO) // auto-increment
    private long id;
    @Column(nullable = false, unique = true, length = 300)
    private String productName;
    private int year;
    private Double price;
    private String url;
    //default constructor
    public Product(){}
    //calculated field = transient
    @Transient
    private int age;
    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - year;
    }


    public Product( String productName, int year, Double price, String url) {

        this.productName = productName;
        this.year = year;
        this.price = price;
        this.url = url;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }


}
