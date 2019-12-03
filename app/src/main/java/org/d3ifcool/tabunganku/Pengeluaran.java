package org.d3ifcool.tabunganku;

import java.io.Serializable;

public class Pengeluaran implements Serializable {

    //class untuk menghubungkan ke Adapter nya (List Pengeluaran Adapter)
    private int idPengeluaran;
    private String name;
    private String telphon;
    private int amount;
    private String description;
    private String dateOfPengeluaran;
    private String dateDue;

    public Pengeluaran(){

    }

    public Pengeluaran(int idPengeluaran, String name, String telphon, int amount, String description, String dateOfLoan, String dateDue) {
        this.idPengeluaran = idPengeluaran;
        this.name = name;
        this.telphon = telphon;
        this.amount = amount;
        this.description = description;
        this.dateOfPengeluaran = dateOfLoan;
        this.dateDue = dateDue;
    }

    public int getidPengeluaran() {
        return idPengeluaran;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTelphon() {
        return telphon;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getDateOfPengeluaran() {
        return dateOfPengeluaran;
    }

    public String getDateDue() {
        return dateDue;
    }

    public void setidPengeluaran(int idPengeluaran) {
        this.idPengeluaran = idPengeluaran;
    }



    public void setTelphon(String telphon) {
        this.telphon = telphon;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateOfPengeluaran(String dateOfPengeluaran) {
        this.dateOfPengeluaran = dateOfPengeluaran;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }
}
