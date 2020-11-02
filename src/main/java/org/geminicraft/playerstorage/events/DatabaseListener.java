package org.geminicraft.playerstorage.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.geminicraft.playerstorage.PlayerStorageClass;
import org.geminicraft.playerstorage.database.CustomTestDatabase;
import org.mineacademy.fo.Common;

public class DatabaseListener implements Listener {

    // Look into changing this to an async event, prior to player logging in.
    private PlayerStorageClass main;

    public DatabaseListener(PlayerStorageClass main) {
        this.main = main;
    }

    private CustomTestDatabase database;
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.database = new CustomTestDatabase(main);
        final Player player = event.getPlayer();
        database.createPlayer(player);

    }

}
