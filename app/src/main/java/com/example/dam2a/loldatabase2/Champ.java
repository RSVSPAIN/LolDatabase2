package com.example.dam2a.loldatabase2;

public class Champ {
    Integer id;
    String posicion;
    String imageName;
    String name;

    public Champ(Integer id, String posicion, String imageName, String name) {
        this.id = id;
        this.posicion = posicion;
        this.imageName = imageName;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}