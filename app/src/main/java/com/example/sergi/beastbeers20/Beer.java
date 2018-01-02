package com.example.sergi.beastbeers20;

import java.util.Date;

/**
 * Created by Sarias on 02/01/2018.
 */

public class Beer {

    private String nombre;
    private String descripción;
    private long abv;
    private int ibu;
    private int styleId;
    private char isOrganic;
    private Date creaciónDate;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public long getAbv() {
        return abv;
    }

    public void setAbv(long abv) {
        this.abv = abv;
    }

    public int getIbu() {
        return ibu;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public char getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(char isOrganic) {
        this.isOrganic = isOrganic;
    }

    public Date getCreaciónDate() {
        return creaciónDate;
    }

    public void setCreaciónDate(Date creaciónDate) {
        this.creaciónDate = creaciónDate;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "nombre='" + nombre + '\'' +
                ", descripción='" + descripción + '\'' +
                ", abv=" + abv +
                ", ibu=" + ibu +
                ", styleId=" + styleId +
                ", isOrganic=" + isOrganic +
                ", creaciónDate=" + creaciónDate +
                "}\n";
    }
}
