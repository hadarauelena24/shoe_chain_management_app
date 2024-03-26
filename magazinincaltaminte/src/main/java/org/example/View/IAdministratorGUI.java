package org.example.View;

import org.example.Model.Utilizator;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IAdministratorGUI extends IGUI {
    int getUtilizatorId();
    void setUtilizatorId(int id);
    String getUtilizatorNume();
    void setUtilizatorNume(String n);
    String getUtilizatorPrenume();
    void setUtilizatorPrenume(String n);
    String getUtilizatorEmail();
    void setUtilizatorEmail(String n);
    String getUtilizatorParola();
    void setUtilizatorParola(String n);
    String getUtilizatorNrTel();
    void setUtilizatorNrTel(String n);
    String getUtilizatorFunctie();
    void setUtilizatorFunctie(String n);
    int getAngajatIdM();
    void setAngajatIdM(int idM);
}
