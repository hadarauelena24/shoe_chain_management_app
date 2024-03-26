package org.example.View;
import org.example.Presenter.AdministratorPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.proteanit.sql.DbUtils;

public class AdministratorGUI extends JFrame implements IAdministratorGUI, ActionListener {
    private AdministratorPresenter administratorPresenter;
    private final JFrame frame = new JFrame("Meniu Administrator");
    private final JButton addU=new JButton("Adauga Utilizator");
    private final JButton findU=new JButton("Cauta Utilizator");
    private final JButton updU=new JButton("Actualizare Utilizator");
    private final JButton delU=new JButton("Stergere Utilizator");
    private final JButton allU=new JButton("Vizualizare Utilizatori");
    private final JButton logoutU=new JButton("Deconectare");
    private final JButton rst=new JButton("Resetare");
    private final JTextField idU=new JTextField();
    private final JTextField numeU=new JTextField();
    private final JTextField prenumeU=new JTextField();
    private final JTextField emailU=new JTextField();
    private final JTextField parolaU=new JTextField();
    private final JTextField nrTelU=new JTextField();
    private final JTextField functieU=new JTextField();
    private final JTextField idM=new JTextField();

    private JTable utilizatoriTable;
    private JScrollPane scrollPane;
    private JPanel panel = new JPanel();
    public AdministratorGUI(){
        this.administratorPresenter=new AdministratorPresenter(this);

        frame.setSize(1100,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);
        panel.setLayout(null);

        JLabel tipUtilizator = new JLabel("Completati datele");
        tipUtilizator.setBounds(10, 20, 300, 25);
        panel.add(tipUtilizator);

        JLabel idL=new JLabel("ID: ");
        idL.setBounds(10,50,100,25);
        panel.add(idL);

        JLabel numeL=new JLabel("Nume: ");
        numeL.setBounds(10,80,100,25);
        panel.add(numeL);
        JLabel prenumeL=new JLabel("Prenume: ");
        prenumeL.setBounds(10,110,100,25);
        panel.add(prenumeL);
        JLabel emailL=new JLabel("Email/Username: ");
        emailL.setBounds(10,140,100,25);
        panel.add(emailL);
        JLabel parolaL=new JLabel("Parola: ");
        parolaL.setBounds(10,170,100,25);
        panel.add(parolaL);
        JLabel adresaL=new JLabel("Nr.Tel: ");
        adresaL.setBounds(10,200,100,25);
        panel.add(adresaL);
        JLabel functieL=new JLabel("Functie: ");
        functieL.setBounds(10,230,100,25);
        panel.add(functieL);
        JLabel idML=new JLabel("ID magazin: ");
        idML.setBounds(10,270,100,25);
        panel.add(idML);

        idU.setBounds(110,50,100,25);
        panel.add(idU);
        numeU.setBounds(110,80,150,25);
        panel.add(numeU);
        prenumeU.setBounds(110,110,150,25);
        panel.add(prenumeU);
        emailU.setBounds(110,140,150,25);
        panel.add(emailU);
        parolaU.setBounds(110,170,150,25);
        panel.add(parolaU);
        nrTelU.setBounds(110,200,150,25);
        panel.add(nrTelU);
        functieU.setBounds(110,230,150,25);
        panel.add(functieU);
        idM.setBounds(110,270,150,25);
        panel.add(idM);

        addU.setBounds(70, 300, 170, 25);
        addU.addActionListener(this);
        panel.add(addU);

        findU.setBounds(70, 330, 170, 25);
        findU.addActionListener(this);
        panel.add(findU);

        updU.setBounds(70, 360, 170, 25);
        updU.addActionListener(this);
        panel.add(updU);

        delU.setBounds(70, 390, 170, 25);
        delU.addActionListener(this);
        panel.add(delU);

        allU.setBounds(270, 50, 170, 25);
        allU.addActionListener(this);
        panel.add(allU);

        logoutU.setBounds(10, 420, 120, 25);
        logoutU.addActionListener(this);
        panel.add(logoutU);

        rst.setBounds(140, 420, 120, 25);
        rst.addActionListener(this);
        panel.add(rst);

        utilizatoriTable=new JTable();
        scrollPane= new JScrollPane(utilizatoriTable);
        scrollPane.setBounds(270, 80, 800, 320);
        panel.add(scrollPane);

        panel.setVisible(true);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addU){
            this.administratorPresenter.addUtilizator();
        }
        if(e.getSource()==findU){
            this.administratorPresenter.findByIdUtilizator();
        }
        if(e.getSource()==updU){
            this.administratorPresenter.updateUtilizator();
        }
        if(e.getSource()==delU){
            this.administratorPresenter.deleteUtilizator();
        }
        if(e.getSource()==allU){
            utilizatoriTable.setModel(DbUtils.resultSetToTableModel(this.administratorPresenter.viewAll()));
        }
        if(e.getSource()==logoutU){
            this.administratorPresenter.openLogin();
        }
        if(e.getSource()==rst){
            this.administratorPresenter.resetFields();
        }
    }
    @Override
    public int getUtilizatorId() {
        return Integer.parseInt(this.idU.getText());
    }

    @Override
    public void setUtilizatorId(int id) {
        this.idU.setText(String.valueOf(id));
    }

    @Override
    public String getUtilizatorNume() {
        return this.numeU.getText();
    }

    @Override
    public void setUtilizatorNume(String n) {
        this.numeU.setText(n);
    }

    @Override
    public String getUtilizatorPrenume() {
        return this.prenumeU.getText();
    }

    @Override
    public void setUtilizatorPrenume(String n) {
        this.prenumeU.setText(n);
    }

    @Override
    public String getUtilizatorEmail() {
        return this.emailU.getText();
    }

    @Override
    public void setUtilizatorEmail(String n) {
        this.emailU.setText(n);
    }

    @Override
    public String getUtilizatorParola() {
        return this.parolaU.getText();
    }

    @Override
    public void setUtilizatorParola(String n) {
        this.parolaU.setText(n);
    }

    @Override
    public String getUtilizatorNrTel() {
        return this.nrTelU.getText();
    }

    @Override
    public void setUtilizatorNrTel(String n) {
        this.nrTelU.setText(n);
    }

    @Override
    public String getUtilizatorFunctie() {
        return this.functieU.getText();
    }

    @Override
    public void setUtilizatorFunctie(String n) {
        this.functieU.setText(n);
    }

    @Override
    public int getAngajatIdM() {
        return Integer.parseInt(this.idM.getText());
    }

    @Override
    public void setAngajatIdM(int idM) {
        this.idM.setText(String.valueOf(idM));
    }

    @Override
    public void setMessage(String title, String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    @Override
    public void disposePage() {
        frame.dispose();
    }


}
