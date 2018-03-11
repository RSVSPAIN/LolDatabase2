package com.example.dam2a.loldatabase2;

public class Champ {
    Integer id;
    String posicion;
    int imageId;
    String name;

    public Champ(Integer id, String posicion, int imageId, String name) {
        this.id = id;
        this.posicion = posicion;
        this.imageId = imageId;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}