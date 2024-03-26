package org.example.View;

import com.mysql.cj.xdevapi.DbDocImpl;
import net.proteanit.sql.DbUtils;
import org.example.Presenter.LoginPresenter;
import org.example.Presenter.ManagerPresenter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerGUI extends JFrame implements IManagerGUI, ActionListener {
    private ManagerPresenter managerPresenter;
    private final JFrame frame = new JFrame("Meniu Manager");
    private final JButton viewAll=new JButton("Vizualizare produse");
    private final JButton filt=new JButton("Filtrare produse");
    private final JButton logout=new JButton("Deconectare");
    private final JTextField filtru=new JTextField();
    private JTable toateProdT;
    private JTable produseFiltT;
    private final JScrollPane scrollPaneTP;
    private final JScrollPane scrollPanePF;
    private JComboBox filtre;
    private String filtreSt[]={"producator","disponibilitate","pret"};
    public ManagerGUI(){
        frame.setSize(700,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel fL=new JLabel("Filtru: ");
        fL.setBounds(10,220,100,25);
        panel.add(fL);

        filtru.setBounds(160,220,100,25);
        panel.add(filtru);

        filtre=new JComboBox(filtreSt);
        filtre.setBounds(50,220,100,25);
        panel.add(filtre);

        toateProdT= new JTable();
        produseFiltT=new JTable();
        scrollPaneTP= new JScrollPane(toateProdT);
        scrollPaneTP.setBounds(10, 50, 600, 150);
        panel.add(scrollPaneTP);
        scrollPanePF= new JScrollPane(produseFiltT);
        scrollPanePF.setBounds(10, 260, 600, 150);
        panel.add(scrollPanePF);

        viewAll.setBounds(10, 10, 170, 25);
        viewAll.addActionListener(this);
        panel.add(viewAll);

        filt.setBounds(270, 220, 170, 25);
        filt.addActionListener(this);
        panel.add(filt);

        logout.setBounds(10, 420, 170, 25);
        logout.addActionListener(this);
        panel.add(logout);

        panel.setVisible(true);
        frame.setVisible(true);
        this.managerPresenter=new ManagerPresenter(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==viewAll){
            toateProdT.setModel(DbUtils.resultSetToTableModel(this.managerPresenter.getAllProduseM()));
        }
        if(e.getSource()==filt){
            produseFiltT.setModel(DbUtils.resultSetToTableModel(this.managerPresenter.filterProduseP()));
        }
        if(e.getSource()==logout){
            this.managerPresenter.openLogin();
        }

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
    public String getFilterT() {
        return this.filtru.getText();
    }

    @Override
    public String getFilter() {
        return (String) this.filtre.getSelectedItem();
    }
}
