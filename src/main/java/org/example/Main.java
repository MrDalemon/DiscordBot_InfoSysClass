package org.example;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandInteraction;

import java.util.concurrent.atomic.AtomicBoolean;


public class Main {
    public static void main(String[] args) {
        AtomicBoolean luckystatus = new AtomicBoolean(false);

        // bot token used for telling the bot the account to use
        Token toke = new Token();
        String token = toke.getToken();
        // the string that creates the bot and logs it in
        DiscordApi api = new DiscordApiBuilder().setToken(token).setAllIntents().login().join();
        //commands TODO: figure out why this command is not listing/working
        SlashCommand test = SlashCommand.with("ping", "checks if the bot is working properly").createGlobal(api).join();


        api.addSlashCommandCreateListener(event -> {
            SlashCommandInteraction interaction = event.getSlashCommandInteraction();
            if (interaction.getFullCommandName().equals("ping")) {
                luckystatus.set(flipBool(luckystatus.get()));
                System.out.println(luckystatus.get());

                // send message back
                interaction.createImmediateResponder().setContent("the status of lucky is"+ luckystatus.get()).respond();

            }
        });
    }

// todo add the module switch commands and test if they work
    public static boolean flipBool(boolean thing){

        thing = !thing;
        System.out.println("The value of the bool is: "+thing);
        return thing;

    }
}