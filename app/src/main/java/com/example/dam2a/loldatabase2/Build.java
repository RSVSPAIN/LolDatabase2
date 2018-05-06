package com.example.dam2a.loldatabase2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raul on 11/03/2018.
 */

public class Build {
    String name;
    String porciento;
    String papel;
    String año;
    Map<String, String> imagenes = new HashMap<>();

    public Build() {
    }

    public Build(String name, String porciento, String papel, String año, String... imagenes) {
        this.name = name;
        this.porciento = porciento;
        this.papel = papel;
        this.año = año;

        for (int i = 0; i < imagenes.length; i++) {
            this.imagenes.put(String.valueOf(i), imagenes[i]);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPorciento() {
        return porciento;
    }

    public void setPorciento(String porciento) {
        this.porciento = porciento;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }
}
