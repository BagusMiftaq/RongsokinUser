package com.edicoding.picodiploma.rongsokinuser.Tambah;

public class Tambah {
    String tambahid;
    String tambahNama;
    String tambahJenis;

    public Tambah(){

    }

    public Tambah(String tambahid, String tambahNama, String tambahJenis) {
        this.tambahid = tambahid;
        this.tambahNama = tambahNama;
        this.tambahJenis = tambahJenis;
    }

    public String getTambahid() {
        return tambahid;
    }

    public String getTambahNama() {
        return tambahNama;
    }

    public String getTambahJenis() {
        return tambahJenis;
    }
}
