package net.hubgame.bansystem.mysql;

import java.sql.*;

public class MySQLConnection {

    public String host;
    public String port;
    public String database;
    public String username;
    public String password;
    public Connection con;

    public boolean connect() {
        if (host != null && port != null && database != null && username != null && password != null) {
            if (!isConnected()) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username,
                            password);
                    System.out.println("MySQL verbindung hergestellt!");
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println(" MySQL verbindung konnte nicht hergestellt werden!");
        }
        return false;
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                con.close();
                System.out.println(" verbindung geschlossen!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResultSet(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isConnected() {
        return con != null;
    }

    public PreparedStatement prepareStatement(String qry) {
        try {
            return con.prepareStatement(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
