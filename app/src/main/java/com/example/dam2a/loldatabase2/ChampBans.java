package com.example.dam2a.loldatabase2;

public class ChampBans {
    Integer id;
    int imageId;
    String name;
    String victorias;
    String banrate;
    String pickrate;

    public ChampBans(Integer id, int imageId, String name, String victorias, String banrate, String pickrate) {
        this.id = id;
        this.imageId = imageId;
        this.name = name;
        this.victorias = victorias;
        this.banrate = banrate;
        this.pickrate = pickrate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getVictorias() {
        return victorias;
    }

    public void setVictorias(String victorias) {
        this.victorias = victorias;
    }

    public String getBanrate() {
        return banrate;
    }

    public void setBanrate(String banrate) {
        this.banrate = banrate;
    }

    public String getPickrate() {
        return pickrate;
    }

    public void setPickrate(String pickrate) {
        this.pickrate = pickrate;
    }
}