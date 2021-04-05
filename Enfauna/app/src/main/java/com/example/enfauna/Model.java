package com.example.enfauna;

public class Model {
    private String id;
    private String nama;
    private String fakta;
    private String penjelasan;
    private String foto;

    public Model(){}

    public Model(String id, String nama, String fakta, String penjelasan, String foto) {
        this.id = id;
        this.nama = nama;
        this.fakta = fakta;
        this.foto = foto;
        this.penjelasan = penjelasan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFakta() {
        return fakta;
    }

    public void setFakta(String fakta) {
        this.fakta = fakta;
    }

    public String getPenjelasan() {
        return penjelasan;
    }

    public void setPenjelasan(String penjelasan) {
        this.penjelasan = penjelasan;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
