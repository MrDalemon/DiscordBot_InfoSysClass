package org.example;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.interaction.SlashCommand;


public class Main {
    public static void main(String[] args) {

        // bot token used for telling the bot the account to use
        Token toke = new Token();
        String token = toke.getToken();
        // the string that creates the bot and logs it in
        DiscordApi api = new DiscordApiBuilder().setToken(token).setAllIntents().login().join();
        //commands TODO: figure out why this command is not listing/working
        SlashCommand test = SlashCommand.with("ping","checks if the bot is working properly").createGlobal(api).join();

        // old way to make commands only here for testing as slash commands do not seem to be working
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")){
                event.getChannel().sendMessage("The bot works the slash command does not");
            }
        });

    }
}