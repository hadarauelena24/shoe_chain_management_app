package org.example.View;

import org.example.Presenter.LoginPresenter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame implements ILoginGUI, ActionListener {
    private LoginPresenter loginPresenter;
    private final JFrame frame = new JFrame("Meniu Login");
    private final JButton connect=new JButton("Conectare");
    private final JTextField emailU=new JTextField();
    private final JTextField parolaU=new JTextField();

    public LoginGUI(){
        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel emailL=new JLabel("Email/Username:");
        emailL.setBounds(10,20,100,25);
        panel.add(emailL);

        JLabel parolaL=new JLabel("Parola: ");
        parolaL.setBounds(10,50,100,25);
        panel.add(parolaL);

        emailU.setBounds(110,20,150,25);
        panel.add(emailU);
        parolaU.setBounds(110,50,150,25);
        panel.add(parolaU);

        connect.setBounds(70, 80, 150, 25);
        connect.addActionListener(this);
        panel.add(connect);

        panel.setVisible(true);
        frame.setVisible(true);
        this.loginPresenter=new LoginPresenter(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==connect){
            this.loginPresenter.findByEmailSiParola();
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
    public String getUtilizatorEmail() {
        return this.emailU.getText();
    }

    @Override
    public String getUtilizatorParola() {
        return this.parolaU.getText();
    }

}
