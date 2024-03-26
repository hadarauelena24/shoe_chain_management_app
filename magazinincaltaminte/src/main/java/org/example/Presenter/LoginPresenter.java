package org.example.Presenter;

import org.example.Model.Angajat;
import org.example.Model.Produs;
import org.example.Model.Repository.AngajatRepo;
import org.example.Model.Repository.UtilizatorRepo;
import org.example.Model.Utilizator;
import org.example.View.*;

public class LoginPresenter {
    private ILoginGUI iLoginGUI;
    private UtilizatorRepo utilizatorRepo;
    private AngajatRepo angajatRepo;
    public LoginPresenter(ILoginGUI iLoginGUI){
        this.iLoginGUI=iLoginGUI;
        utilizatorRepo=new UtilizatorRepo();
        angajatRepo=new AngajatRepo();
    }
    public void findByEmailSiParola() {
        String email = this.iLoginGUI.getUtilizatorEmail();
        String parola = this.iLoginGUI.getUtilizatorParola();
        int ok = 2;
        if (email == null || email.isEmpty()) {
            this.iLoginGUI.setMessage("Incomplete info!", "email-ul utilizatorului e gol");
            ok--;
        }
        if (parola == null || parola.isEmpty()) {
            this.iLoginGUI.setMessage("Incomplete info!", "parola utilizatorului e goala");
            ok--;
        }
        System.out.println("ok:"+ok);
        if (ok == 2) {
            Utilizator utilizator = utilizatorRepo.findByEmailSiParola(email, parola);
            if (utilizator != null) {
                String functie = utilizator.getFunctie();
                this.iLoginGUI.disposePage();
                if (functie.equals("angajat")) {
                    Angajat angajat = angajatRepo.findByIdUtilizatorA(utilizator.getIdUtilizator());
                    if (angajat != null) {
                        AngajatGUI angajatGUI = new AngajatGUI(angajat.getIdM());
                    }
                } else if (functie.equals("administrator")) {
                    AdministratorGUI administratorGUI = new AdministratorGUI();
                } else if (functie.equals("manager")) {
                    ManagerGUI managerGUI = new ManagerGUI();
                }
            }
            else {
                this.iLoginGUI.setMessage("esec", "Nu exista niciun cont cu credentialele introduse");
            }
        }
    }
}