package org.example.Model;

import java.util.List;

public class ProdusPart {
    private int idPP;
    private String culoare;
    private int marime;
    private int disponibilitate;
    private int idM;
    private int idP;
    public ProdusPart() {
    }
    public ProdusPart(int idPP, String culoare, int marime, int disponibilitate, int idM, int idP) {
        this.idPP = idPP;
        this.culoare = culoare;
        this.marime = marime;
        this.disponibilitate = disponibilitate;
        this.idM = idM;
        this.idP = idP;
    }
    public int getIdPP() {
        return idPP;
    }
    public String getCuloare() {
        return culoare;
    }
    public int getMarime() {
        return marime;
    }
    public int getDisponibilitate() {
        return disponibilitate;
    }
    public int getIdM() {
        return idM;
    }
    public void setIdM(int idM) {
        this.idM = idM;
    }
    public int getIdP() {
        return idP;
    }

}
