package org.example.Model;

public class Angajat extends Utilizator{
    private int idM;
    public Angajat(){}
    public Angajat(int idU, String n, String p, String e, String pa, String nt, String f,int idM){
        super(idU,n,p,e,pa,nt,f);
        this.idM=idM;
    }
    public Angajat( int idM) {
        this.idM=idM;
    }
    public int getIdM() {
        return idM;
    }
    public void setIdM(int idM) {
        this.idM = idM;
    }
}
