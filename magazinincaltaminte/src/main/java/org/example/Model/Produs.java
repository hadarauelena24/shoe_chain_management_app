package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class Produs {
    private int idP;
    private String denumire;
    private String producator;
    private float pret;
    public Produs() {
    }
    public Produs(int idP, String denumire, String producator, float pret) {
        this.idP = idP;
        this.denumire = denumire;
        this.producator = producator;
        this.pret = pret;
    }
    public int getIdP() {
        return idP;
    }
    public String getDenumire() {
        return denumire;
    }
    public String getProducator() {
        return producator;
    }
    public float getPret() {
        return pret;
    }
}
