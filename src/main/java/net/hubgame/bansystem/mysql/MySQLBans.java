package net.hubgame.bansystem.mysql;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MySQLBans {

    private MySQLConnection mySQLConnection;

    public MySQLBans() {
        mySQLConnection = new MySQLConnection();
        mySQLConnection.database = "";
        mySQLConnection.host = "";
        mySQLConnection.port = "";
        mySQLConnection.password = "";
        mySQLConnection.update("CREATE TABLE IF NOT EXISTS Bans (uuid VARCHAR(40), ip VARCHAR(100), id INT(2), time VARCHAR(100))");
    }

    public void addBan(UUID playerUUID, String ipAdress, int id, long banTimeStamp) {
        mySQLConnection.update("INSERT INTO Bans (uuid, ip, id, time) VALUES ('" + playerUUID + "', '" + ipAdress + "', '" + id + "', '" + banTimeStamp + "')");
    }

    public void removeBan(String ipAdress) {
        mySQLConnection.update("DELETE FROM `bans` WHERE `ip`='" + ipAdress + "'");
    }

    public void removeBan(UUID uuid) {
        mySQLConnection.update("DELETE FROM `bans` WHERE `uuid`='" + uuid + "'");
    }

    public boolean isBanned(UUID uuid) {
        ResultSet resultSet = mySQLConnection.getResultSet("SELECT `id` FROM `bans` WHERE `uuid`='" + uuid + "'");
        try {
            if (resultSet.wasNull()) {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isBanned(String ipAdress) {
        ResultSet resultSet = mySQLConnection.getResultSet("SELECT `id` FROM `bans` WHERE `ip`='" + ipAdress + "'");
        try {
            if (resultSet.wasNull()) {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public String getID(String ipAdress) {
        String id = "";
        try {
            ResultSet resultSet = mySQLConnection.getResultSet("SELECT `id` FROM `bans` WHERE `ip` = '" + ipAdress + "'");
            id = resultSet.getString(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public int getID(UUID uuid) {
        int id = -1;
        try {
            ResultSet resultSet = mySQLConnection.getResultSet("SELECT `id` FROM `bans` WHERE `uuid` = '" + uuid + "'");
            id = resultSet.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public long getTime(String ipAdress) {
        long time = -1;
        try {
            ResultSet resultSet = mySQLConnection.getResultSet("SELECT `time` FROM `bans` WHERE `ip`='" + ipAdress + "'");
            String stringTime = resultSet.getString(1);
            if (!stringTime.equalsIgnoreCase("")) {
                time = Long.parseLong(stringTime);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return time;
    }


    public long getTime(UUID uuid) {
        long time = -1;
        try {
            ResultSet resultSet = mySQLConnection.getResultSet("SELECT `time` FROM `bans` WHERE `uuid`='" + uuid + "'");
            time = resultSet.getLong(1);
            String stringTime = resultSet.getString(1);
            if (!stringTime.equalsIgnoreCase("")) {
                time = Long.parseLong(stringTime);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return time;
    }


}
