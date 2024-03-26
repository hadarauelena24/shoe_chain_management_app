package org.example.Presenter;

import org.example.Model.Repository.ProdusRepo;
import org.example.View.IManagerGUI;
import org.example.View.LoginGUI;

import java.sql.ResultSet;

public class ManagerPresenter {
    private IManagerGUI iManagerGUI;
    private ProdusRepo produsRepo;
    public ManagerPresenter(IManagerGUI iManagerGUI){
        this.iManagerGUI=iManagerGUI;
        produsRepo=new ProdusRepo();
    }
    public ResultSet getAllProduseM() {
            ResultSet rs= this.produsRepo.viewAllProducts();
            return rs;
    }
    public ResultSet filterProduseP() {
        String tipfiltru=this.iManagerGUI.getFilter();
        String filtru=this.iManagerGUI.getFilterT();
        if(filtru==null || filtru.isEmpty()){
            this.iManagerGUI.setMessage("esec","trebuie sa specificati valoarea dupa care doriti filtrarea");
            return null;
        }else{
            ResultSet rs= this.produsRepo.filterProd(tipfiltru,filtru);
            return rs;
        }
    }
    public void openLogin() {
        this.iManagerGUI.disposePage();
        LoginGUI loginGUI=new LoginGUI();
    }
}
