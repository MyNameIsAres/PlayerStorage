package org.geminicraft.playerstorage.conversations;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.geminicraft.playerstorage.PlayerStorageClass;
import org.geminicraft.playerstorage.database.GeminiQueryClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.fo.conversation.SimplePrompt;

import java.util.Arrays;

public class VerifyInGameConversation extends SimplePrompt {

    private PlayerStorageClass mainPlugin;
//    private GeminiQueryClass database;

    public VerifyInGameConversation(final PlayerStorageClass mainPlugin) {
        this.mainPlugin = mainPlugin;
    }


    // TODO: VerifyInGameConversation::class should have no knowledge of all the queries.
    private GeminiQueryClass getDatabaseInstance() {
        return new GeminiQueryClass(mainPlugin);
    }

    @Override
    protected String getPrompt(ConversationContext context) {
        final Player player = getPlayer(context);
        return "&3" + player.getName() + " you signed up! Type no if you did not, yes if you did.";
    }

    @Override
    protected @Nullable Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
        final Player player = getPlayer(context);

        if ("yes".equals(input.toLowerCase())) {
            tell(player, "&6Your account has been verified!");

//            this.database = new GeminiQueryClass(mainPlugin);
            getDatabaseInstance().updateVerifiedPlayerDataTable(player);
            getDatabaseInstance().updateVerifiedUsersTable(player);
//            database.updateVerifiedUsersTable(player);
//            database.updateVerifiedPlayerDataTable(player);

        }
        else if ("no".equals(input.toLowerCase())) {
            //TODO Generate some sort of report to send to the admin
            tell(player, "&6[Security]: Account was not authorized. Account has been frozen. Report sent to administrators.");
        }

        return Prompt.END_OF_CONVERSATION;
    }

    @Override
    protected boolean isInputValid(ConversationContext context, String input) {
        String userInput = input.toLowerCase();
        return Arrays.asList("yes", "no").contains(userInput);
    }

}
