package org.example.Model.Repository;

import org.example.Model.Angajat;
import org.example.Model.Utilizator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AngajatRepo {
    private Repository repository;

    public AngajatRepo() {
        this.repository = new Repository();
    }
    public Boolean addAngajat(Angajat a){
        String cmdSql="insert into angajat values('";
        cmdSql+=a.getIdUtilizator()+"','"+a.getIdM()+"');";
        return this.repository.executeSQL(cmdSql);
    }
    public Angajat findByIdUtilizatorA(int idUtilizator) {
        Angajat angajat = null;
        String query = "SELECT * FROM angajat WHERE idU = ?";
        repository.openConnection();
        Connection connection = this.repository.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idUtilizator); // Setarea parametrului idUtilizator
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                angajat = new Angajat(
                        resultSet.getInt("idM")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return angajat;
    }
    public boolean updateAngajat(Angajat angajat){
        String query = "UPDATE angajat SET idM=? WHERE idU = ?";
        repository.openConnection();
        Connection connection = this.repository.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, angajat.getIdM());
            preparedStatement.setInt(2, angajat.getIdUtilizator()); // Setarea parametrului idUtilizator
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteAngajat(int id){
        String query = "DELETE FROM angajat WHERE idU = ?";
        repository.openConnection();
        Connection connection = this.repository.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
