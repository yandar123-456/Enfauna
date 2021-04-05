package com.example.enfauna;

public class Contact {
    private String id;
    private String nama;
    private String telpon;
    private String email;
    private String alamat;
    private String foto;

    public Contact(){}

    public Contact(String id, String nama, String telpon, String email, String alamat, String foto) {
        this.id = id;
        this.nama = nama;
        this.telpon = telpon;
        this.email = email;
        this.alamat = alamat;
        this.foto = foto;
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

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
