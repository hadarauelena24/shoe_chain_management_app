package org.example.Model.Repository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Repository {
    private static final Logger LOGGER= Logger.getLogger(Repository.class.getName());
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String DBURL="jdbc:mysql://localhost:3306/magazinincaltaminte";
    private static  final String USER="root";
    private static final String PASS="31Babe08.";
    protected Connection connection;

    public Repository() {
        try{
            Class.forName(DRIVER);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection(DBURL,USER,PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"An error occured while trying to connect to the database");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void openConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                // Reconectare, dacă este necesar
                this.connection = DriverManager.getConnection(DBURL,USER,PASS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean executeSQL(String sql) {
        boolean result = true;
        this.openConnection();
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 0) {
                    result = false;
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                result = false;
            //throw new RuntimeException(ex);
             }finally {
            this.closeConnection();
             }
        return result;
    }

    public ResultSet getTable(String sql) {
        ResultSet resultSet = null;
        this.openConnection();
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Notă: Nu închide conexiunea dacă intenționezi să folosești ResultSet în afara acestei metode.
            // closeConnection();
        }
        return resultSet;
    }
}
