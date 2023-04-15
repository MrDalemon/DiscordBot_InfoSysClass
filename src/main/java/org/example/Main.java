package org.example;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.interaction.SlashCommandOptionBuilder;
import org.javacord.api.interaction.SlashCommandOptionChoice;

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

        SlashCommand luckyStatus = SlashCommand.with("Lucky Status","sets the activity of the lucky module using a Boolean value Default : False ",
                SlashCommandOptionChoice.create("option",)).setDefaultEnabledForPermissions(PermissionType.ADMINISTRATOR).createGlobal(api).join();


        api.addSlashCommandCreateListener(event -> {
            SlashCommandInteraction interaction = event.getSlashCommandInteraction();
            if (interaction.getFullCommandName().equals("ping")) {

                // print to the console
                System.out.println(interaction.getUser().getName().toString()+" used the command ping");
                interaction.createImmediateResponder().setContent("this is full of shit ");

            }
        });

        api.addSlashCommandCreateListener(event -> {
            SlashCommandInteraction interaction = event.getSlashCommandInteraction();
            if (interaction.getFullCommandName().equals("Lucky Status")) {

                // print to the console
                System.out.println(interaction.getUser().getName().toString()+" used the command ping");
                // get the value for it and check if it is good
                interaction.getArgumentAttachmentValueByName("option").isPresent();

                if interaction.getar


                // send message back
                interaction.createImmediateResponder().setContent("the status of lucky is"+ luckystatus.get()).respond();
                //todo add the module activation or deactivation events


            }
        });
    }



    }



// todo add the module switch commands and test if they work
    public static boolean flipBool(boolean boolToBeChanged){

        boolToBeChanged = !boolToBeChanged;
        System.out.println("The value of the bool is: "+boolToBeChanged);
        return boolToBeChanged;

    }
}