package org.geminicraft.playerstorage.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.geminicraft.playerstorage.PlayerStorageClass;
import org.geminicraft.playerstorage.conversations.VerifyInGameConversation;
import org.geminicraft.playerstorage.database.GeminiQueryClass;
import org.mineacademy.fo.Common;


public class DatabaseListener implements Listener {

    //TODO Look into changing this to an async event, prior to player logging in.
    private PlayerStorageClass main;

    public DatabaseListener(PlayerStorageClass main) {
        this.main = main;
    }

    private GeminiQueryClass database;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.database = new GeminiQueryClass(main);
        final Player player = event.getPlayer();
        database.createPlayer(player);

        // TODO: Externalize to either verification class or db class?
        if (database.playerHasCreatedAccount(player)) {
            Common.log("has created account");
            if (!database.hasVerifiedInGame(player)) {

                new VerifyInGameConversation(main).show(player);
            }
        }


    }


}
