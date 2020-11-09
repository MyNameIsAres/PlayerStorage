package org.geminicraft.playerstorage;

import org.bukkit.Bukkit;
import org.geminicraft.playerstorage.database.GeminiDatabase;
import org.geminicraft.playerstorage.database.GeminiQueryClass;
import org.geminicraft.playerstorage.events.DatabaseListener;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.plugin.SimplePlugin;
import java.sql.SQLException;

public class PlayerStorageClass extends SimplePlugin {


    public GeminiDatabase database;
    public GeminiQueryClass queries;

    @Override
    protected void onPluginStart() {

        Common.log("PlayerStorage connected");

        this.database = new GeminiDatabase();
        this.queries = new GeminiQueryClass(this);

        try {
            database.connect();
        } catch (ClassNotFoundException | SQLException exception) {
            Bukkit.getLogger().info("Database not connected");
            exception.printStackTrace();
        }

        if (database.isConnected()) {
            Bukkit.getLogger().info("Database is connected");
            registerEvents(new DatabaseListener(this));

        }
    }

    @Override
    protected void onPluginStop() {
        database.disconnect();
    }
}
