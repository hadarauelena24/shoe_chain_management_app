package org.example.Presenter;

import org.example.Model.*;
import org.example.Model.Repository.ProdusPartRepo;
import org.example.Model.Repository.ProdusRepo;
import org.example.View.IAngajatGUI;
import org.example.View.LoginGUI;

import java.sql.ResultSet;

public class AngajatPresenter {
    private IAngajatGUI iAngajatGUI;
    private ProdusRepo produsRepo;
    private ProdusPartRepo produsPartRepo;

    public AngajatPresenter(IAngajatGUI iAngajatGUI) {
        this.iAngajatGUI=iAngajatGUI;
        produsRepo=new ProdusRepo();
        produsPartRepo=new ProdusPartRepo();
    }
    private Produs validInfoP(){
        int idP=this.iAngajatGUI.getProdusId();
        if(idP==0){
            this.iAngajatGUI.setMessage("Incomplete info!","Id-ul produsului trebuie sa fie un nr diferit de 0");
            return null;
        }
        String denumire=this.iAngajatGUI.getProdusDenumire();
        if(denumire==null || denumire.isEmpty()){
            this.iAngajatGUI.setMessage("Incomplete info!","Numele produsului e gol");
            return null;
        }
        String producator=this.iAngajatGUI.getProdusProducator();
        if(producator==null || producator.isEmpty()){
            this.iAngajatGUI.setMessage("Incomplete info!","numele producatorului produsului e gol");
            return null;
        }
        float pret=this.iAngajatGUI.getProdusPret();
        if(pret==0){
            this.iAngajatGUI.setMessage("Incomplete info!","pretul produsului e gol");
            return null;
        }

        return new Produs(idP,denumire,producator,pret);
    }
    private ProdusPart validInfoPP(int idMA){
        int idPP=this.iAngajatGUI.getProdusPartId();
        if(idPP==0){
            this.iAngajatGUI.setMessage("Incomplete info!","Id-ul partii produsului sa fie un nr diferit de 0");
            return null;
        }
        String culoare=this.iAngajatGUI.getProdusPartCuloare();
        if(culoare==null || culoare.isEmpty()){
            this.iAngajatGUI.setMessage("Incomplete info!","Culoarea partii produsului e goala");
            return null;
        }
        int marime=this.iAngajatGUI.getProdusPartMarime();
        if(marime==0){
            this.iAngajatGUI.setMessage("Incomplete info!","Marimea partii produsului sa fie un nr diferit de 0");
            return null;
        }
        int disponibilitate=this.iAngajatGUI.getProdusPartDisponibilitate();
        if(disponibilitate==0){
            this.iAngajatGUI.setMessage("Incomplete info!","Stocul partii produsului sa fie un nr diferit de 0");
            return null;
        }
        int idM=this.iAngajatGUI.getProdusPartIdM();
        if(idM==0 || idM!=idMA){
            this.iAngajatGUI.setMessage("Incomplete info!","Id-ul magazinului partii produsului sa fie un nr diferit de 0 si sa fie cel al magazinului unde lucreaza angajatul");
            return null;
        }
        int idP=this.iAngajatGUI.getProdusId();
        if(idP==0){
            this.iAngajatGUI.setMessage("Incomplete info!","Id-ul produsului trebuie sa fie un nr diferit de 0");
            return null;
        }
        return new ProdusPart(idPP,culoare,marime,disponibilitate,idM,idP);
    }

    public void addProdus(int idMA) {

        try{
            Produs produs=this.validInfoP();
            ProdusPart produsPart=this.validInfoPP(idMA);
            System.out.println("idM "+idMA);
            System.out.println(produs.getIdP());
            System.out.println(produsPart.getIdPP()+" "+ produsPart.getIdP());
            if(produs!=null && produsPart!=null){
                boolean res=this.produsRepo.addProdus(produs);
                if(res){
                    boolean ppres=this.produsPartRepo.addProdusPart(produsPart);
                    if(ppres){
                         this.iAngajatGUI.setMessage("succes!","adaugarea produsului si a partii s-a realizat cu succes");
                    }
                    else{
                        this.iAngajatGUI.setMessage("Esec!","adaugarea produsului si a partii nu s-a realizat");
                    }
                } else {
                    // Aici poți face ceva specific doar pentru obiectele de tip Utilizator
                    this.iAngajatGUI.setMessage("Esec!","adaugarea produsului nu s-a realizat");
                }
            }
            }catch(Exception exception){
                this.iAngajatGUI.setMessage("add-exceptie","datele nu au fost validate");
            }
    }
    public void addProdusPart(int idMA) {
        try {
            int idP = this.iAngajatGUI.getProdusId();
            if (idP == 0) {
                this.iAngajatGUI.setMessage("Incomplete info!", "Id-ul produsului pentru care doriti sa adaugati produsul particular trebuie sa fie un nr diferit de 0");
            } else {
                //try{
                Produs produs = this.produsRepo.findPByIdP(idP);
                if (produs != null) {
                    this.iAngajatGUI.setProdusId(produs.getIdP());
                    this.iAngajatGUI.setProdusDenumire(produs.getDenumire());
                    this.iAngajatGUI.setProdusProducator(produs.getProducator());
                    this.iAngajatGUI.setProdusPret(produs.getPret());
                    ProdusPart produsPart = this.validInfoPP(idMA);
                    System.out.println(produsPart.getIdPP() + " " + produsPart.getIdP());
                    if (produsPart != null) {
                        boolean ppres = this.produsPartRepo.addProdusPart(produsPart);
                        if (ppres) {
                            this.iAngajatGUI.setMessage("succes!", "adaugarea partii produsului si a partii s-a realizat cu succes");
                        } else {
                            this.iAngajatGUI.setMessage("Esec!", "adaugarea partii produsului si a partii nu s-a realizat");
                        }
                    }
                } else {
                    this.iAngajatGUI.setMessage("Esec!", "Pentru a adauga un produs particular la un produs deja existent, trebuie introdus id-ul produsului pentru care se doreste adaugarea.");
                }
            }
        } catch (Exception e) {
            this.iAngajatGUI.setMessage("add part-exceptie", "trebuie introdus id-ul produsului pentru care se doreste adaugarea.");
        }
    }
    public void findByIdProdus() {
        int idPP=this.iAngajatGUI.getProdusPartId();
        if(idPP==0){
            this.iAngajatGUI.setMessage("Incomplete info!","Id-ul partii produsului trebuie sa fie un nr diferit de 0");
        }
        else{
            System.out.println("id Ut: "+idPP);
            ProdusPart produsPart=produsPartRepo.findPPByIdPP(idPP);
            //ProdusPart produsPart=produsPartRepo.findPPByIdPP(idP);
            if(produsPart!=null){
                Produs produs=this.produsRepo.findPByIdP(produsPart.getIdP());
                if(produs!=null){
                    this.iAngajatGUI.setProdusId(produs.getIdP());
                    this.iAngajatGUI.setProdusDenumire(produs.getDenumire());
                    this.iAngajatGUI.setProdusProducator(produs.getProducator());
                    this.iAngajatGUI.setProdusPret(produs.getPret());
                    this.iAngajatGUI.setProdusPartCuloare(produsPart.getCuloare());
                    this.iAngajatGUI.setProdusPartMarime(produsPart.getMarime());
                    this.iAngajatGUI.setProdusPartDisponibilitate(produsPart.getDisponibilitate());
                    this.iAngajatGUI.setProdusPartIdM(produsPart.getIdM());
                }
            }
            else{
                this.iAngajatGUI.setMessage("id-exceptie","Id-ul introdus nu corespunde niciunui produs");
            }
        }
    }

    public void updateProdus(int idMA) {
        try{
            Produs produs=this.validInfoP();
            ProdusPart produsPart=this.validInfoPP(idMA);
            System.out.println("idM "+idMA);
            System.out.println(produs.getIdP());
            System.out.println(produsPart.getIdPP()+" "+ produsPart.getIdP());
            if(produs!=null && produsPart!=null){
                boolean res=this.produsRepo.updateProdus(produs);
                if(res){
                    boolean ppres=this.produsPartRepo.updateProdusPart(produsPart);
                    if(ppres){
                        this.iAngajatGUI.setMessage("succes!","actualizarea produsului si a partii s-a realizat cu succes");
                    }
                    else{
                        this.iAngajatGUI.setMessage("Esec!","actualizarea produsului si a partii nu s-a realizat");
                    }
                } else {
                    this.iAngajatGUI.setMessage("Esec!","actualizarea produsului nu s-a realizat");
                }
            }
        }catch(Exception exception){
            this.iAngajatGUI.setMessage("actualizare-exceptie","datele nu au fost validate");
        }
    }

    public void deleteProdusPart(int idMA) {
        int idPP=this.iAngajatGUI.getProdusPartId();
        if(idPP==0){
            this.iAngajatGUI.setMessage("Incomplete info!","Id-ul partii produsului trebuie sa fie un nr diferit de 0");
        }
        else{
            ProdusPart produsPart=this.produsPartRepo.findPPByIdPP(idPP);
            if(produsPart!=null){
                if(produsPart.getIdM()==idMA){
                    boolean res=this.produsPartRepo.deleteProdusPart(idPP);
                    if(res){
                        this.iAngajatGUI.setMessage("succes","produsul particular a fost sters");
                    }
                    else{
                        this.iAngajatGUI.setMessage("esec","produsul particular nu a putut fi sters");
                    }
                }
                else{
                    this.iAngajatGUI.setMessage("esec","produsul particular nu poate fi sters decat daca se afla in magazinul unde lucrati");
                }
            }
            else{
                this.iAngajatGUI.setMessage("esec","nu a fost gasit niciun produs particular cu acest id");
            }
        }

    }

    public ResultSet getAllProduseP() {
        int idMSel=this.iAngajatGUI.getIdMSelectat();
        if(idMSel==0){
            this.iAngajatGUI.setMessage("esec","id-ul magazinului selectat trebuie sa fie diferit de 0");
            return null;
        }else{
            ResultSet rs= this.produsRepo.viewAllProductsP(idMSel);
            return rs;
        }
    }
    public ResultSet filterProduseP() {
        String tipfiltru=this.iAngajatGUI.getFilter();
        String filtru=this.iAngajatGUI.getFilterT();
        if(filtru==null || filtru.isEmpty()){
            this.iAngajatGUI.setMessage("esec","trebuie sa specificati valoarea dupa care doriti filtrarea");
            return null;
        }else{
            ResultSet rs= this.produsRepo.filterProd(tipfiltru,filtru);
            return rs;
        }
    }
    public void openLogin() {
        this.iAngajatGUI.disposePage();
        LoginGUI loginGUI=new LoginGUI();
    }

    public ResultSet getAllProduse() {
        ResultSet rs= this.produsRepo.viewAllProducts();
        return rs;
    }

    public void resetFields(int idMA){
        this.iAngajatGUI.setProdusId(0);
        this.iAngajatGUI.setProdusDenumire(null);
        this.iAngajatGUI.setProdusProducator(null);
        this.iAngajatGUI.setProdusPret(0);
        this.iAngajatGUI.setProdusPartCuloare(null);
        this.iAngajatGUI.setProdusPartMarime(0);
        this.iAngajatGUI.setProdusPartDisponibilitate(0);
        this.iAngajatGUI.setProdusPartIdM(idMA);
        this.iAngajatGUI.setProdusPartId(0);
    }
}
