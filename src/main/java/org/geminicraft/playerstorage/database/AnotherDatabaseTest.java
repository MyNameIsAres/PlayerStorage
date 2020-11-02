package org.geminicraft.playerstorage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AnotherDatabaseTest {

    private String host = "";
    private String port = "";
    private String database = "";
    private String username = "";
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
