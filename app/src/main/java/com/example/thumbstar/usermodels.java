package com.example.thumbstar;

public class usermodels {
    private String email;
    private String password;
    private String nama;
    private String notelp;
    private String alamat;
    private String roles;

    public usermodels(){}

    public usermodels(String email, String password, String nama, String notelp, String alamat) {
        this.email = email;
        this.password = password;
        this.nama = nama;
        this.notelp = notelp;
        this.alamat = alamat;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getRoles() {
        return roles;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }

    public String getNotelp() {
        return notelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}