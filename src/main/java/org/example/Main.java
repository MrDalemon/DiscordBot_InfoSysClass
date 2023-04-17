package org.example;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.Attachment;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.permission.RoleBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.interaction.*;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


public class Main {
    public static void main(String[] args) {
        AtomicBoolean luckyStatus = new AtomicBoolean(false);
        AtomicBoolean colorStatus = new AtomicBoolean(false);
        AtomicBoolean managemntStatus = new AtomicBoolean(false);

        // bot token used for telling the bot the account to use
        Token toke = new Token();
        String token = toke.getToken();
        // the string that creates the bot and logs it in

        DiscordApi api = new DiscordApiBuilder().setToken(token).setAllIntents().login().join();

        //Ping will be deleted later
        SlashCommand test = SlashCommand.with("ping", "checks if the bot is working properly").createGlobal(api).join();

        // lucky module commands
        SlashCommand LuckyStatus = SlashCommand.with("luckystatus","Flips the status of Lucky Module, Default = False").setDefaultEnabledForPermissions(PermissionType.ADMINISTRATOR).createGlobal(api).join();
        SlashCommand CoinFlip = SlashCommand.with("coinflip","Flips a coin and returns heads or tails").createGlobal(api).join();
        SlashCommand d4 = SlashCommand.with("d4","Generates a random number between 1 and 4").createGlobal(api).join();
        SlashCommand d6 = SlashCommand.with("d6","Generates a random number between 1 and 6").createGlobal(api).join();
        SlashCommand d8 = SlashCommand.with("d8","Generates a random number between 1 and 8").createGlobal(api).join();
        SlashCommand d10 = SlashCommand.with("d10","Generates a random number between 1 and 10").createGlobal(api).join();
        SlashCommand d20 = SlashCommand.with("d20","Generates a random number between 1 and 20").createGlobal(api).join();

        // Color Module commands
        SlashCommand ColorStatus = SlashCommand.with("colorstatus","Flips the status of Color Module, Default = False").setDefaultEnabledForPermissions(PermissionType.ADMINISTRATOR).createGlobal(api).join();
        SlashCommand Color = SlashCommand.with("color","Adds colors to you ", Arrays.asList(
                SlashCommandOption.create(SlashCommandOptionType.STRING,"HEX","hexadecimal color value",true)
        )).createGlobal(api).join();


        //Management Commands
        SlashCommand ManagementStatus = SlashCommand.with("managementstatus","Flips the status of Management Module, Default = False").setDefaultEnabledForPermissions(PermissionType.ADMINISTRATOR).createGlobal(api).join();

        //when a command is used return
        api.addSlashCommandCreateListener(event -> {
            SlashCommandInteraction interaction = event.getSlashCommandInteraction();

            // Lucky Module command responces
            if (interaction.getFullCommandName().equals("luckystatus")) {
                luckyStatus.set(flipBool(luckyStatus.get()));
                System.out.println(interaction.getUser().getName().toString()+ " used command luckystatus and changed it to : "+luckyStatus.get());
                interaction.createImmediateResponder().setContent("the status of lucky is " + luckyStatus.get()).respond();
            }
            if (luckyStatus.get() == false){
                // if lucky module is disabled
                if (interaction.getFullCommandName().equals("coinflip")){
                    System.out.println(interaction.getUser().getName().toString()+ " used command coinflip but lucky is disabled");
                    interaction.createImmediateResponder().setContent("the module Lucky is disabled please contact an admin to turn it on  ").respond();
                }
                if (interaction.getFullCommandName().equals("d4")){
                    System.out.println(interaction.getUser().getName().toString()+ " used command d4 but lucky is disabled");
                    interaction.createImmediateResponder().setContent("the module Lucky is disabled please contact an admin to turn it on  ").respond();
                }
                if (interaction.getFullCommandName().equals("d6")){
                    System.out.println(interaction.getUser().getName().toString()+ " used command d6 but lucky is disabled");
                    interaction.createImmediateResponder().setContent("the module Lucky is disabled please contact an admin to turn it on  ").respond();
                }
                if (interaction.getFullCommandName().equals("d8")){
                    System.out.println(interaction.getUser().getName().toString()+ " used command d8 but lucky is disabled");
                    interaction.createImmediateResponder().setContent("the module Lucky is disabled please contact an admin to turn it on  ").respond();
                }
                if (interaction.getFullCommandName().equals("d10")){
                    System.out.println(interaction.getUser().getName().toString()+ " used command d10 but lucky is disabled");
                    interaction.createImmediateResponder().setContent("the module Lucky is disabled please contact an admin to turn it on  ").respond();
                }
                if (interaction.getFullCommandName().equals("d20")){
                    System.out.println(interaction.getUser().getName().toString()+ " used command d20 but lucky is disabled");
                    interaction.createImmediateResponder().setContent("the module Lucky is disabled please contact an admin to turn it on  ").respond();
                }
            }else{
                // if lucky module is enabled
                if (interaction.getFullCommandName().equals("coinflip")){
                    System.out.println(interaction.getUser().getName().toString()+ " used command coinflip");
                    if(generateRandom(2)== 0){
                        interaction.createImmediateResponder().setContent("The result of the coinflip is Heads").respond();
                    }else{
                        interaction.createImmediateResponder().setContent("The result of the coinflip is Tails").respond();
                    }
                }
                if (interaction.getFullCommandName().equals("d4")){
                    System.out.println(interaction.getUser().getName().toString()+ " used command d20");
                    interaction.createImmediateResponder().setContent("The result of the d4 roll is: "+ generateRandom(20)).respond();
                }
                if (interaction.getFullCommandName().equals("d6")){
                    System.out.println(interaction.getUser().getName().toString()+ " used command d6");
                    interaction.createImmediateResponder().setContent("The result of the d6 roll is: "+ generateRandom(6)).respond();
                }
                if (interaction.getFullCommandName().equals("d8")){
                    System.out.println(interaction.getUser().getName().toString()+ " used command d8");
                    interaction.createImmediateResponder().setContent("The result of the d8 roll is: "+ generateRandom(8)).respond();
                }
                if (interaction.getFullCommandName().equals("d10")){
                    System.out.println(interaction.getUser().getName().toString()+ " used command d10");
                    interaction.createImmediateResponder().setContent("The result of the d10 roll is: "+ generateRandom(10)).respond();
                }
                if (interaction.getFullCommandName().equals("d20")){
                    System.out.println(interaction.getUser().getName().toString()+ " used command d20");
                    interaction.createImmediateResponder().setContent("The result of the d20 roll is: "+ generateRandom(20)).respond();
                }
            }

            // color module command responces
            if (interaction.getFullCommandName().equals("colorstatus")) {
                colorStatus.set(flipBool(colorStatus.get()));
                System.out.println(interaction.getUser().getName().toString()+ " used command colorstatus and changed it to : "+colorStatus.get());
                interaction.createImmediateResponder().setContent("the status of Color Module is " + colorStatus.get()).respond();
            }

            if(colorStatus.get() == false){
                if(interaction.getFullCommandName().equals("color")){
                    System.out.println(interaction.getUser().getName().toString()+" color command was used with module not enables");
                    interaction.createImmediateResponder().setContent("Color module not enabled please contact an adminstrator to fix the issue").respond();
                }
            }else{
                if(interaction.getFullCommandName().equals("color")){
                   Optional<Attachment> color = interaction.getArgumentAttachmentValueByName("HEX");

                   User user = interaction.getUser();
                    //Role colorRole = (Role) new RoleBuilder(interaction.getServer().get()).setName("color");
                    //java.awt.Color color1 = new
                   // colorRole.updateColor();
                  // user.addRole(colorRole);
                   interaction.createImmediateResponder().setContent("bot is recieving a color "+color.toString()).respond();
                   System.out.println(color);
                }
            }

            // management command responces
            if (interaction.getFullCommandName().equals("managementstatus")) {
                managemntStatus.set(flipBool(managemntStatus.get()));
                System.out.println(interaction.getUser().getName().toString()+ " used command management and changed it to : "+managemntStatus.get());
                interaction.createImmediateResponder().setContent("the status of Management Module  is " + managemntStatus.get()).setFlags(MessageFlag.EPHEMERAL).respond();
            }

            if(managemntStatus.get() == false){
                // that says the module is disabled
            }else{
                //code that changes colors
            }

            // ping command
            if (interaction.getFullCommandName().equals("ping")) {
                System.out.println(interaction.getUser().getName().toString()+ " used Command ping");
                interaction.createImmediateResponder().setContent("the bot is working as expected").respond();
            }
        });
    }

    public static boolean flipBool(boolean boolToFlip){

        boolToFlip = !boolToFlip;
        System.out.println("The value of the bool is: "+boolToFlip);
        return boolToFlip;
    }

    public static int generateRandom(int random){

        Random rdm = new Random();
        return rdm.nextInt(random);
    }
}