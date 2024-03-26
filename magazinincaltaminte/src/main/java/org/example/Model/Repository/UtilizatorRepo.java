package org.example.Model.Repository;

import org.example.Model.Utilizator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UtilizatorRepo {
    private Repository repository;
    public UtilizatorRepo(){
        this.repository=new Repository();
    }
    public Boolean addUtilizator(Utilizator utilizator){
        String cmdSql="insert into Utilizator values('";
        cmdSql+=utilizator.getIdUtilizator()+"','"+utilizator.getNume()+"','"+utilizator.getPrenume()+"','"+utilizator.getEmail()+"','"+utilizator.getParola()+"','"+utilizator.getNrTel()+"','"+utilizator.getFunctie()+"');";
        return this.repository.executeSQL(cmdSql);
    }
    public Utilizator findByIdUtilizator(int idUtilizator) {
        Utilizator utilizator = null;
        String query = "SELECT * FROM Utilizator WHERE idUtilizator = ?";
        repository.openConnection();
       // PreparedStatement preparedStatement = repository.getConnection().prepareStatement(selectSQL);
        Connection connection = this.repository.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idUtilizator); // Setarea parametrului idUtilizator
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                utilizator = new Utilizator(
                        resultSet.getInt("idUtilizator"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("email"),
                        resultSet.getString("parola"),
                        resultSet.getString("nrTel"),
                        resultSet.getString("functie")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilizator;
    }
    public Utilizator findByEmailSiParola(String email, String parola) {
        Utilizator utilizator = null;
        String query = "SELECT * FROM Utilizator WHERE email = ? and parola=? ";
        repository.openConnection();
        Connection connection = this.repository.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email); // Setarea parametrului idUtilizator
            preparedStatement.setString(2, parola);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                utilizator = new Utilizator(
                        resultSet.getInt("idUtilizator"),
                        resultSet.getString("nume"),
                        resultSet.getString("prenume"),
                        resultSet.getString("email"),
                        resultSet.getString("parola"),
                        resultSet.getString("nrTel"),
                        resultSet.getString("functie")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilizator;
    }
    public boolean updateUtilizator(Utilizator utilizator){
        String query = "UPDATE Utilizator set nume=?,prenume=?,email=?,parola=?,nrTel=?,functie=? WHERE idUtilizator = ?";
        repository.openConnection();
        Connection connection = this.repository.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, utilizator.getNume());
            preparedStatement.setString(2, utilizator.getPrenume());
            preparedStatement.setString(3, utilizator.getEmail());
            preparedStatement.setString(4, utilizator.getParola());
            preparedStatement.setString(5, utilizator.getNrTel());
            preparedStatement.setString(6, utilizator.getFunctie()); //
            preparedStatement.setInt(7, utilizator.getIdUtilizator()); // Setarea parametrului idUtilizator
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteUtilizator(int idUtilizator) {
        String query = "DELETE FROM Utilizator WHERE idUtilizator=?";
        repository.openConnection();
        Connection connection = repository.getConnection();
        try {PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idUtilizator);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet viewAll(){
        String query = "SELECT u.idUtilizator, u.nume, u.prenume, u.email, u.nrTel, u.functie, a.idM\n" + "FROM utilizator u\n" + "LEFT JOIN angajat a ON u.idUtilizator = a.idU;";
        ResultSet rs= repository.getTable(query);
        return rs;
    }
}
