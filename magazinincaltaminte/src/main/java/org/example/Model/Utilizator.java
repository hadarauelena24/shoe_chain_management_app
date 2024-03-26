package org.example.Model;

public class Utilizator {
    private int idUtilizator;
    private String nume;
    private String prenume;
    private String email;
    private String parola;
    private String nrTel;
    private String functie;
    public Utilizator() {}
    public Utilizator(int idUtilizator, String nume, String prenume, String email, String parola, String nrTel, String functie) {
        this.idUtilizator = idUtilizator;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.parola = parola;
        this.nrTel = nrTel;
        this.functie = functie;
    }
    public int getIdUtilizator() {
        return idUtilizator;
    }
    public String getNume() {
        return nume;
    }
    public String getPrenume() {
        return prenume;
    }
    public String getEmail() {
        return email;
    }
    public String getParola() {
        return parola;
    }
    public String getNrTel() {
        return nrTel;
    }
    public String getFunctie() {
        return functie;
    }
}
