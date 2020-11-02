package org.geminicraft.playerstorage;

import org.bukkit.Bukkit;
import org.geminicraft.playerstorage.database.AnotherDatabaseTest;
import org.geminicraft.playerstorage.database.CustomTestDatabase;
import org.geminicraft.playerstorage.events.DatabaseListener;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.plugin.SimplePlugin;

import java.sql.SQLException;

public class PlayerStorageClass extends SimplePlugin {

    public AnotherDatabaseTest test;
    public CustomTestDatabase database;

    @Override
    protected void onPluginStart() {
        Common.log("PlayerStorage connected");

        this.test = new AnotherDatabaseTest();
        this.database = new CustomTestDatabase(this);

        try {
            test.connect();
        } catch (ClassNotFoundException | SQLException exception) {
            Bukkit.getLogger().info("Database not connected");
            exception.printStackTrace();
        }

        if (test.isConnected()) {
            Bukkit.getLogger().info("Database is connected");
            registerEvents(new DatabaseListener(this));

        }
    }

    @Override
    protected void onPluginStop() {
        test.disconnect();
    }
}
