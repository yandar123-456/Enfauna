package com.example.enfauna;

public class Model1 {
    private String id;
    private String nama;
    private String asal;
    private String penjelasan;
    private String status;
    private String foto;

    public Model1(){}


    public Model1(String id, String nama, String asal, String penjelasan, String status, String foto) {
        this.id = id;
        this.nama = nama;
        this.asal = asal;
        this.penjelasan = penjelasan;
        this.status = status;
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

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getPenjelasan() {
        return penjelasan;
    }

    public void setPenjelasan(String penjelasan) {
        this.penjelasan = penjelasan;
    }

    public String getStatus() {
        return status;
    }

    public void setStaus(String staus) {
        this.status = staus;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
