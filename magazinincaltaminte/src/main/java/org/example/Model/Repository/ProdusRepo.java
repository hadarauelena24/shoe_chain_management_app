package org.example.Model.Repository;

import org.example.Model.Produs;
import org.example.Model.Utilizator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdusRepo {
    private Repository repository;

    public ProdusRepo() {
        repository=new Repository();
    }
    public boolean addProdus(Produs produs){
        //int sizepp=produs.getProdusePart().size();
        String cmdSql="insert into Produs values('";
        cmdSql+=produs.getIdP()+"','"+produs.getDenumire()+"','"+produs.getProducator()+"','"+ produs.getPret()+"');";
        return this.repository.executeSQL(cmdSql);
    }
    public Produs findPByIdP(int id) {
        Produs produs = null;
        String query = "SELECT * FROM produs WHERE idP = ?";
        repository.openConnection();
        Connection connection = this.repository.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id); // Setarea parametrului idUtilizator
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                produs = new Produs(
                        resultSet.getInt("idP"),
                        resultSet.getString("denumire"),
                        resultSet.getString("producator"),
                        resultSet.getFloat("pret")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produs;
    }
    public boolean updateProdus(Produs produs){
        String query = "UPDATE produs set denumire=?,producator=?,pret=? WHERE idP = ?";
        repository.openConnection();
        Connection connection = this.repository.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, produs.getDenumire());
            preparedStatement.setString(2, produs.getProducator());
            preparedStatement.setFloat(3, produs.getPret());
            preparedStatement.setInt(4, produs.getIdP());
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public ResultSet viewAllProducts(){
        String query="SELECT p.idP, p.denumire AS 'Denumire Produs', p.producator AS 'Producator', p.pret AS 'Pret',pp.idPP, pp.culoare AS 'Culoare', pp.marime AS 'Marime', pp.disponibilitate AS 'Disponibilitate',pp.idM FROM produs p JOIN produspart pp ON p.idP = pp.idP JOIN magazin m ON pp.idM = m.idM order by m.idM";
        ResultSet rs= repository.getTable(query);
        return rs;
    }
    public ResultSet viewAllProductsP(int idMA){
        String query="SELECT p.idP, p.denumire AS 'Denumire Produs', p.producator AS 'Producator', p.pret AS 'Pret',pp.idPP, pp.culoare AS 'Culoare', pp.marime AS 'Marime', pp.disponibilitate AS 'Disponibilitate',pp.idM FROM produs p JOIN produspart pp ON p.idP = pp.idP JOIN magazin m ON pp.idM = m.idM where m.idM='"+idMA+"' order by pret";
        ResultSet rs= repository.getTable(query);
        return rs;
    }

    public ResultSet filterProd(String tipfiltru,String filtru) {
        String query="SELECT p.idP, p.denumire AS 'Denumire Produs', p.producator AS 'Producator', p.pret AS 'Pret',pp.idPP, pp.culoare AS 'Culoare', pp.marime AS 'Marime', pp.disponibilitate AS 'Disponibilitate',pp.idM FROM produs p JOIN produspart pp ON p.idP = pp.idP JOIN magazin m ON pp.idM = m.idM WHERE "+tipfiltru+"='"+filtru+"'";
        ResultSet rs= repository.getTable(query);
        return rs;
    }
}
