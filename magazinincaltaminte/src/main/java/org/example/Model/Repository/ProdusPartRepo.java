package org.example.Model.Repository;

import org.example.Model.Produs;
import org.example.Model.ProdusPart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdusPartRepo {
    Repository repository;
    public ProdusPartRepo() {
        repository=new Repository();
    }
    public boolean addProdusPart(ProdusPart produsPart){
        String cmdSql="insert into produspart values('";
        cmdSql+=produsPart.getIdPP()+"','"+produsPart.getCuloare()+"','"+produsPart.getMarime()+"','"+produsPart.getDisponibilitate()+"','"+ produsPart.getIdM()+"','"+ produsPart.getIdP()+"');";
        return this.repository.executeSQL(cmdSql);
    }
    public ProdusPart findPPByIdPP(int id) {
        ProdusPart produsPart = null;
        repository.openConnection();
        String query = "SELECT * FROM produspart WHERE idPP = ?";
        Connection connection = this.repository.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id); // Setarea parametrului idUtilizator
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                produsPart = new ProdusPart(
                        resultSet.getInt("idPP"),
                        resultSet.getString("culoare"),
                        resultSet.getInt("marime"),
                        resultSet.getInt("disponibilitate"),
                        resultSet.getInt("idM"),
                        resultSet.getInt("idP")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produsPart;
    }
    public boolean updateProdusPart(ProdusPart produsPart){
        String query = "UPDATE produspart set culoare=?,marime=?,disponibilitate=?,idM=?,idP=? WHERE idPP = ?";
        repository.openConnection();
        Connection connection = this.repository.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, produsPart.getCuloare());
            preparedStatement.setInt(2, produsPart.getMarime());
            preparedStatement.setInt(3, produsPart.getDisponibilitate());
            preparedStatement.setInt(4, produsPart.getIdM());
            preparedStatement.setInt(5, produsPart.getIdP());
            preparedStatement.setInt(6, produsPart.getIdPP());
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteProdusPart(int id) {
        String query = "DELETE FROM produspart WHERE idPP=?";
        repository.openConnection();
        Connection connection = repository.getConnection();
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
