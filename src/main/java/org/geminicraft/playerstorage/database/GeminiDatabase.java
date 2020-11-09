package org.geminicraft.playerstorage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GeminiDatabase {

    private String host = "localhost";
    private String port = "3306";
    private String database = "geminicraft_test_database";
    private String username = "root";
    private String password = "";

    private Connection connection;

    public boolean isConnected() {
        return (connection == null ? false : true);
    }

    public void connect() throws ClassNotFoundException, SQLException {
        if (!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" +
                    host + ":" + port + "/" + database + "?useSSL=false",
                    username, password);
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }




}
