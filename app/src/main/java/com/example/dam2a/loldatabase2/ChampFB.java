package com.example.dam2a.loldatabase2;

public class ChampFB {
    Integer id;
    String posicion;
    String name;
    String imageURL;


    public ChampFB(Integer id, String posicion, String imageURL, String name) {
        this.id = id;
        this.posicion = posicion;
        this.imageURL = imageURL;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}