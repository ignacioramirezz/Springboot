package com.example.sprindatajpa;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Coche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //representa la clave primaria

    private Integer year;
    private String model;
    private String manufacturer;


    //Constructores

    public Coche(){

    }

    public Coche(Long id, Integer year, String model, String manufacturer) {
        this.id = id;
        this.year = year;
        this.model = model;
        this.manufacturer = manufacturer;
    }

    //Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    // toString


    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", year=" + year +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
