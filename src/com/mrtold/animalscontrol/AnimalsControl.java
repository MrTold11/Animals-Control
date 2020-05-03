package com.mrtold.animalscontrol;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class AnimalsControl extends JavaPlugin {

    Logger log = getLogger();
    public double[] area = new double[3];
    public String[] msg = new String[2];

    public void onEnable() {
        loadConfig();
        getCommand("anc").setExecutor(new Commands(this));
        getServer().getPluginManager().registerEvents(new BreadingListener(this), this);
        log.info("Plugin enabled");
    }

    public void loadConfig() {
        saveDefaultConfig();
        reloadConfig();
        area[0] = getConfig().getDouble("area.x")/2;
        area[1] = getConfig().getDouble("area.y")/2;
        area[2] = getConfig().getDouble("area.z")/2;
        msg[0] = getConfig().getString("messages.failed-to-breed");
        msg[1] = getConfig().getString("messages.too-many-animals");
    }

}
