package org.example.View;

import net.proteanit.sql.DbUtils;
import org.example.Presenter.AngajatPresenter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AngajatGUI extends JFrame implements IAngajatGUI, ActionListener {
    private AngajatPresenter angajatPresenter;
    private final JFrame frame = new JFrame("Meniu Angajat");
    private final JButton addP = new JButton("Adauga Produs");
    private final JButton findP = new JButton("Cauta Produs Part");
    private final JButton updP = new JButton("Actualizare Produs");
    private final JButton delP = new JButton("Stergere ProdusPart");
    private final JButton allP = new JButton("Vizualizare Produse");
    private final JButton addPP = new JButton("Adauga ProdusPart");
    private final JButton viewAll = new JButton("Toate produsele");
    private final JButton logout = new JButton("Deconectare");
    private final JButton filt = new JButton("Filtrare");
    private final JButton rst = new JButton("Resetare");
    private final JTextField idP = new JTextField();
    private final JTextField denumireP = new JTextField();
    private final JTextField producatorP = new JTextField();
    private final JTextField pretP = new JTextField();
    private final JTextField idPP = new JTextField();
    private final JTextField culoareP = new JTextField();
    private final JTextField marimeP = new JTextField();
    private final JTextField dispP = new JTextField();
    private final JTextField idM = new JTextField();
    private final JTextField idMSelectat = new JTextField();
    private final JTextField filtruT = new JTextField();
    private JComboBox filtre;

    private final JTable produseTable;
    private final JTable produseF;
    //private final DefaultTableModel tableModel;
    private final JScrollPane scrollPane;
    private final JScrollPane scrollPaneF;

    private int idMA;
    private String filtreSt[]={"producator","disponibilitate","pret"};

    public AngajatGUI(int idM) {
        frame.setSize(1200, 390);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel tipProdus = new JLabel("Completati datele pentru Produs");
        tipProdus.setBounds(10, 20, 300, 25);
        panel.add(tipProdus);

        JLabel idL = new JLabel("ID Produs: ");
        idL.setBounds(5, 50, 100, 25);
        panel.add(idL);

        JLabel denumireL = new JLabel("Denumire: ");
        denumireL.setBounds(5, 80, 100, 25);
        panel.add(denumireL);

        JLabel producatorL = new JLabel("Producator: ");
        producatorL.setBounds(5, 110, 100, 25);
        panel.add(producatorL);

        JLabel pretL = new JLabel("Pret: ");
        pretL.setBounds(5, 140, 100, 25);
        panel.add(pretL);

        JLabel idPPL = new JLabel("ID ProdusPart: ");
        idPPL.setBounds(230, 50, 100, 25);
        panel.add(idPPL);

        JLabel culoareL = new JLabel("Culoare: ");
        culoareL.setBounds(230, 80, 100, 25);
        panel.add(culoareL);

        JLabel marimeL = new JLabel("Marime: ");
        marimeL.setBounds(230, 110, 100, 25);
        panel.add(marimeL);

        JLabel dispL = new JLabel("Stoc: ");
        dispL.setBounds(230, 140, 100, 25);
        panel.add(dispL);

        JLabel idML = new JLabel("ID Magazin: ");
        idML.setBounds(230, 170, 100, 25);
        panel.add(idML);

        JLabel idMSL = new JLabel("ID Magazin Selectat: ");
        idMSL.setBounds(470, 50, 130, 25);
        panel.add(idMSL);

        JLabel filtruL = new JLabel("Filtru: ");
        filtruL.setBounds(470, 200, 80, 25);
        panel.add(filtruL);

        filtruT.setBounds(640, 200, 100, 25);
        panel.add(filtruT);

        filtre=new JComboBox(filtreSt);
        filtre.setBounds(530,200,100,25);
        panel.add(filtre);

        idMSelectat.setBounds(610, 50, 50, 25);
        panel.add(idMSelectat);

        idP.setBounds(70, 50, 100, 25);
        panel.add(idP);

        denumireP.setBounds(70, 80, 150, 25);
        panel.add(denumireP);

        producatorP.setBounds(70, 110, 150, 25);
        panel.add(producatorP);

        pretP.setBounds(70, 140, 150, 25);
        panel.add(pretP);

        idPP.setBounds(330, 50, 100, 25);
        panel.add(idPP);

        culoareP.setBounds(300, 80, 150, 25);
        panel.add(culoareP);

        marimeP.setBounds(300, 110, 150, 25);
        panel.add(marimeP);

        dispP.setBounds(300, 140, 150, 25);
        panel.add(dispP);

        this.idM.setBounds(300, 170, 150, 25);
        panel.add(this.idM);

        produseTable = new JTable();
        scrollPane = new JScrollPane(produseTable);
        scrollPane.setBounds(470, 80, 600, 100);
        panel.add(scrollPane);

        produseF = new JTable();
        scrollPaneF = new JScrollPane(produseF);
        scrollPaneF.setBounds(470, 230, 600, 100);
        panel.add(scrollPaneF);

        addP.setBounds(50, 200, 170, 25);
        addP.addActionListener(this);
        panel.add(addP);

        findP.setBounds(280, 230, 170, 25);
        findP.addActionListener(this);
        panel.add(findP);

        updP.setBounds(50, 230, 170, 25);
        updP.addActionListener(this);
        panel.add(updP);

        delP.setBounds(280, 260, 170, 25);
        delP.addActionListener(this);
        panel.add(delP);

        allP.setBounds(690, 50, 170, 25);
        allP.addActionListener(this);
        panel.add(allP);

        addPP.setBounds(280, 200, 170, 25);
        addPP.addActionListener(this);
        panel.add(addPP);

        logout.setBounds(50, 300, 170, 25);
        logout.addActionListener(this);
        panel.add(logout);

        filt.setBounds(750, 200, 170, 25);
        filt.addActionListener(this);
        panel.add(filt);

        viewAll.setBounds(880, 50, 170, 25);
        viewAll.addActionListener(this);
        panel.add(viewAll);

        rst.setBounds(50, 260, 170, 25);
        rst.addActionListener(this);
        panel.add(rst);

        panel.setVisible(true);
        frame.setVisible(true);

        this.angajatPresenter = new AngajatPresenter(this);
        this.idMA=idM;
        setProdusPartIdM(idM);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addP) {
            this.angajatPresenter.addProdus(idMA);
        }
        if (e.getSource() == findP) {
            this.angajatPresenter.findByIdProdus();
        }
        if (e.getSource() == updP) {
            this.angajatPresenter.updateProdus(idMA);
        }
        if (e.getSource() == delP) {
            this.angajatPresenter.deleteProdusPart(idMA);
        }
        if (e.getSource() == allP) {
            produseTable.setModel(DbUtils.resultSetToTableModel(angajatPresenter.getAllProduseP()));
        }
        if (e.getSource() == addPP) {
            this.angajatPresenter.addProdusPart(idMA);
        }
        if (e.getSource() == filt) {
           produseF.setModel(DbUtils.resultSetToTableModel(this.angajatPresenter.filterProduseP()));
        }
        if (e.getSource() == viewAll) {
            produseTable.setModel(DbUtils.resultSetToTableModel(this.angajatPresenter.getAllProduse()));
        }
        if(e.getSource()==logout){
            this.angajatPresenter.openLogin();
        }
        if(e.getSource()==rst){
            this.angajatPresenter.resetFields(idMA);
        }
    }

    @Override
    public int getProdusId() {
        return Integer.parseInt(this.idP.getText());
    }

    @Override
    public void setProdusId(Integer id) {
        this.idP.setText(String.valueOf(id));
    }

    @Override
    public String getProdusDenumire() {
        return this.denumireP.getText();
    }

    @Override
    public void setProdusDenumire(String denumire) {
        this.denumireP.setText(denumire);
    }

    @Override
    public String getProdusProducator() {
        return this.producatorP.getText();
    }

    @Override
    public void setProdusProducator(String producator) {
        this.producatorP.setText(producator);
    }

    @Override
    public float getProdusPret() {
        return Float.parseFloat(this.pretP.getText());
    }

    @Override
    public void setProdusPret(double pret) {
        this.pretP.setText(String.valueOf(pret));
    }

    @Override
    public void setProdusPartIdM(Integer id) {
        this.idM.setText(String.valueOf(id));
    }

    @Override
    public void setMessage(String title, String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    @Override
    public void disposePage() {
        frame.dispose();
    }

    @Override
    public int getProdusPartId() {
        return Integer.parseInt(this.idPP.getText());
    }

    @Override
    public String getProdusPartCuloare() {
        return this.culoareP.getText();
    }

    @Override
    public int getProdusPartMarime() {
        return Integer.parseInt(this.marimeP.getText());
    }

    @Override
    public int getProdusPartDisponibilitate() {
        return Integer.parseInt(this.dispP.getText());
    }

    @Override
    public int getProdusPartIdM() {
        return Integer.parseInt(this.idM.getText());
    }

    @Override
    public void setProdusPartCuloare(String culoare) {
        this.culoareP.setText(culoare);
    }

    @Override
    public void setProdusPartMarime(Integer marime) {
        this.marimeP.setText(String.valueOf(marime));
    }

    @Override
    public void setProdusPartDisponibilitate(Integer disponibilitate) {
        this.dispP.setText(String.valueOf(disponibilitate));
    }

    @Override
    public int getIdMSelectat() {
        return Integer.parseInt(this.idMSelectat.getText());
    }

    @Override
    public String getFilter() {
        return (String) this.filtre.getSelectedItem();
    }

    @Override
    public String getFilterT() {
        return this.filtruT.getText();
    }

    @Override
    public void setProdusPartId(int i) {
        this.idPP.setText(String.valueOf(i));
    }


}