package com.example.dam2a.loldatabase2;

/**
 * Created by Raul on 21/03/2018.
 */

public class Video {

    Integer id;
    String titulo;
    String videoName;

    public Video() {}

    public Video(Integer id, String titulo, String videoName) {
        this.id = id;
        this.titulo = titulo;
        this.videoName = videoName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }
}
