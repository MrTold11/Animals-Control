package com.mrtold.animalscontrol;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class Commands implements CommandExecutor {

    AnimalsControl plugin;

    public Commands(AnimalsControl a) {
        plugin = a;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 0 && strings[0].equals("reload")) {
            if (commandSender.hasPermission("animalscontrol.reload")) {
                plugin.loadConfig();
                commandSender.sendMessage("§a[ANC] Configuration reloaded!");
            } else
                commandSender.sendMessage("§a[ANC] §cYou haven't permission");
        } else
            commandSender.sendMessage("§eUsage: /anc reload");
        return true;
    }
}
