package org.d3ifcool.tabunganku;

import java.io.Serializable;

public class Pemasukkan implements Serializable {
    //class untuk menghubungkan ke Adapter nya (List Pengeluaran Adapter)
    private int idPemasukkan;
    private int amount_pem;
    private String desc_pem;
    private String dateOfPemasukkan;

    public Pemasukkan(){

    }

    public Pemasukkan(int idPemasukkan, int amount_pem, String desc_pem, String dateOfPemasukkan) {
        this.idPemasukkan = idPemasukkan;
        this.amount_pem = amount_pem;
        this.desc_pem = desc_pem;
        this.dateOfPemasukkan = dateOfPemasukkan;
    }

    public int getIdPemasukkan() {
        return idPemasukkan;
    }

    public void setIdPemasukkan(int idPemasukkan) {
        this.idPemasukkan = idPemasukkan;
    }

    public int getAmount_pem() {
        return amount_pem;
    }

    public void setAmount_pem(int amount_pem) {
        this.amount_pem = amount_pem;
    }

    public String getDesc_pem() {
        return desc_pem;
    }

    public void setDesc_pem(String desc_pem) {
        this.desc_pem = desc_pem;
    }

    public String getDateOfPemasukkan() {
        return dateOfPemasukkan;
    }

    public void setDateOfPemasukkan(String dateOfPemasukkan) {
        this.dateOfPemasukkan = dateOfPemasukkan;
    }
}
