package org.example.Presenter;

import org.example.Model.Angajat;
import org.example.Model.Repository.AngajatRepo;
import org.example.Model.Repository.UtilizatorRepo;
import org.example.Model.Utilizator;
import org.example.View.IAdministratorGUI;
import org.example.View.LoginGUI;
import java.sql.ResultSet;


public class AdministratorPresenter {
    private IAdministratorGUI iAdministratorGUI;
    private UtilizatorRepo utilizatorRepo;
    private AngajatRepo angajatRepo;
    public AdministratorPresenter(IAdministratorGUI iAdministratorGUI){
        this.iAdministratorGUI=iAdministratorGUI;
        this.utilizatorRepo =new UtilizatorRepo();
        this.angajatRepo=new AngajatRepo();
    }
    private Utilizator validInformation(){
        int idUtilizator=this.iAdministratorGUI.getUtilizatorId();
        if(idUtilizator==0){
            this.iAdministratorGUI.setMessage("Incomplete info!","Id-ul utilizatorului trebuie sa fie un nr diferit de 0");
            return null;
        }
        String nume=this.iAdministratorGUI.getUtilizatorNume();
        if(nume==null || nume.isEmpty()){
            this.iAdministratorGUI.setMessage("Incomplete info!","Numele utilizatorului e gol");
            return null;
        }
        String prenume=this.iAdministratorGUI.getUtilizatorPrenume();
        if(prenume==null || prenume.isEmpty()){
            this.iAdministratorGUI.setMessage("Incomplete info!","prenumele utilizatorului e gol");
            return null;
        }
        String email=this.iAdministratorGUI.getUtilizatorEmail();
        if(email==null || email.isEmpty()){
            this.iAdministratorGUI.setMessage("Incomplete info!","email-ul utilizatorului e gol");
            return null;
        }
        String parola=this.iAdministratorGUI.getUtilizatorParola();
        if(parola==null || parola.isEmpty()){
            this.iAdministratorGUI.setMessage("Incomplete info!","parola utilizatorului e goala");
            return null;
        }
        String nrTel=this.iAdministratorGUI.getUtilizatorNrTel();
        if(nrTel==null || nrTel.isEmpty()){
            this.iAdministratorGUI.setMessage("Incomplete info!","Nr. telefonului utilizatorului e gol");
            return null;
        }
        String functie=this.iAdministratorGUI.getUtilizatorFunctie();
        if(functie==null || functie.isEmpty()){
            this.iAdministratorGUI.setMessage("Incomplete info!","Functia utilizatorului e goala");
            return null;
        }
        else if(!(functie.equalsIgnoreCase("angajat") || functie.equalsIgnoreCase("administrator") || functie.equalsIgnoreCase("manager") )){
            this.iAdministratorGUI.setMessage("Wrong info!","Functia utilizatorului nu este una acceptata. Trebuie sa fie de tip: angajat, manager sau administrator");
            return null;
        }
        else if(functie.equalsIgnoreCase("angajat")) {
            int idM=this.iAdministratorGUI.getAngajatIdM();
            if(idM==0) {
                this.iAdministratorGUI.setMessage("Incomplete info!","Id-ul magazinului trebuie sa fie un nr diferit de 0 pentru un angajat");
                return null;
            }
            else
                return new Angajat(idUtilizator, nume, prenume, email, parola, nrTel, functie,idM);
        }
        return new Utilizator(idUtilizator,nume,prenume,email,parola,nrTel,functie);
    }
    public void addUtilizator(){
        try{
            Utilizator utilizator=this.validInformation();
            System.out.println(utilizator.toString());
            System.out.println(utilizator.getNume());
            if(utilizator!=null){
                boolean res=this.utilizatorRepo.addUtilizator(utilizator);
                if(res){
                    if(utilizator instanceof Angajat){
                        Angajat angajat = (Angajat) utilizator;
                        System.out.println("Este un angajat: " + angajat.getNume());
                        boolean ares=this.angajatRepo.addAngajat(angajat);
                        if(ares){
                            this.iAdministratorGUI.setMessage("succes!","adaugarea utilizatorului si angajatului s-a realizat cu succes");
                        }
                        else{
                            this.iAdministratorGUI.setMessage("Esec!","adaugarea angajatului  nu s-a realizat");
                        }
                    } else {
                        this.iAdministratorGUI.setMessage("succes!","adaugarea utlizatorului s-a realizat cu succes");
                        System.out.println("Este un utilizator: " + utilizator.getNume());
                    }
                }
                else{
                    this.iAdministratorGUI.setMessage("Esec!","adaugarea utlizatorului  nu s-a realizat");
                }
            }
        }catch(Exception exception){
            this.iAdministratorGUI.setMessage("add-exceptie","datele nu au fost validate");
        }
    }
    public void findByIdUtilizator(){
        int idUtilizator=this.iAdministratorGUI.getUtilizatorId();
        if(idUtilizator==0){
            this.iAdministratorGUI.setMessage("Incomplete info!","Id-ul utilizatorului trebuie sa fie un nr diferit de 0");
        }
        else{
            System.out.println("id Ut: "+idUtilizator);
            Utilizator utilizator=utilizatorRepo.findByIdUtilizator(idUtilizator);
            Angajat angajat=null;
            if(utilizator!=null){
                this.iAdministratorGUI.setUtilizatorId(utilizator.getIdUtilizator());
                this.iAdministratorGUI.setUtilizatorNume(utilizator.getNume());
                this.iAdministratorGUI.setUtilizatorPrenume(utilizator.getPrenume());
                this.iAdministratorGUI.setUtilizatorEmail(utilizator.getEmail());
                this.iAdministratorGUI.setUtilizatorParola(utilizator.getParola());
                this.iAdministratorGUI.setUtilizatorNrTel(utilizator.getNrTel());
                this.iAdministratorGUI.setUtilizatorFunctie(utilizator.getFunctie());
                if(utilizator.getFunctie().equals("angajat")) {
                    angajat = angajatRepo.findByIdUtilizatorA(idUtilizator);
                    if(angajat!=null){
                        this.iAdministratorGUI.setAngajatIdM(angajat.getIdM());
                    }
                }
            }
            else{
                this.iAdministratorGUI.setMessage("id-exceptie","Id-ul introdus nu corespunde niciunui utilizator");
            }
        }
    }
    public void updateUtilizator(){
        try {
            Utilizator utilizator = this.validInformation();
            if (utilizator != null) {
                boolean updated = utilizatorRepo.updateUtilizator(utilizator);
                if (updated) {
                    if (utilizator instanceof Angajat) {
                        Angajat angajat = (Angajat) utilizator;
                        System.out.println("Este un angajat: " + angajat.getNume());
                        boolean ares = this.angajatRepo.updateAngajat(angajat);
                        if (ares) {
                            this.iAdministratorGUI.setMessage("succes!", "actualizarea utilizatorului si a angajatului s-a realizat cu succes");
                        } else {
                            this.iAdministratorGUI.setMessage("Esec!", "actualizarea angajatului  nu s-a realizat");
                        }
                    }
                } else {
                    this.iAdministratorGUI.setMessage("esec", "Actualizarea utilizatorului a esuat");
                }
            }
        } catch (Exception e) {
            this.iAdministratorGUI.setMessage("upd_except", "datele nu au fost validate");
        }
    }
    public void deleteUtilizator(){
        int idUtilizator=this.iAdministratorGUI.getUtilizatorId();
        if(idUtilizator==0){
            this.iAdministratorGUI.setMessage("Incomplete info!","Id-ul utilizatorului trebuie sa fie un nr diferit de 0");
        }
        else{
            System.out.println("id Ut: "+idUtilizator);
            Utilizator utilizator=utilizatorRepo.findByIdUtilizator(idUtilizator);
            boolean ares,res;
                if(utilizator!=null){
                    if(utilizator.getFunctie().equals("angajat")){
                        ares = angajatRepo.deleteAngajat(idUtilizator);
                        if (ares) {
                            res = utilizatorRepo.deleteUtilizator(idUtilizator);
                            if (res) {
                                this.iAdministratorGUI.setMessage("succes", "stergerea utilizatorului s-a realizat cu succes");
                            } else {
                                this.iAdministratorGUI.setMessage("esec", "stergerea utilizatorului a esuat");
                            }
                        } else {
                            this.iAdministratorGUI.setMessage("esec", "stergerea angajatului a esuat");
                        }
                    }
                    else{
                        res = utilizatorRepo.deleteUtilizator(idUtilizator);
                        if (res) {
                            this.iAdministratorGUI.setMessage("succes", "stergerea utilizatorului s-a realizat cu succes");
                        } else {
                            this.iAdministratorGUI.setMessage("esec", "stergerea utilizatorului a esuat");
                        }
                    }
                }
            else{
                this.iAdministratorGUI.setMessage("id-exceptie","Id-ul introdus nu corespunde niciunui utilizator");
            }
        }
    }
    public ResultSet viewAll(){
        ResultSet rs=this.utilizatorRepo.viewAll();
        return rs;
    }

    public void openLogin() {
        this.iAdministratorGUI.disposePage();
        LoginGUI loginGUI=new LoginGUI();
    }
    public void resetFields(){
        this.iAdministratorGUI.setUtilizatorId(0);
        this.iAdministratorGUI.setUtilizatorNume(null);
        this.iAdministratorGUI.setUtilizatorPrenume(null);
        this.iAdministratorGUI.setUtilizatorEmail(null);
        this.iAdministratorGUI.setUtilizatorParola(null);
        this.iAdministratorGUI.setUtilizatorNrTel(null);
        this.iAdministratorGUI.setUtilizatorFunctie(null);
        this.iAdministratorGUI.setAngajatIdM(0);
    }
}
