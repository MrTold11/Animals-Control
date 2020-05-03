package com.mrtold.animalscontrol;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;

import java.util.concurrent.ThreadLocalRandom;

public class BreadingListener implements Listener {

    AnimalsControl plugin;

    public BreadingListener(AnimalsControl p) {
        plugin = p;
    }

    @EventHandler
    public void onBreedEvent(EntityBreedEvent event) {
        EntityType n = event.getMother().getType();
        int a = plugin.getConfig().getInt("breeding-chance." + n.toString().toLowerCase(), -1);
        int b = plugin.getConfig().getInt("population-limits." + n.toString().toLowerCase(), -1);
        if (a > -1 && b > -1)
            if (checkArea(event.getMother(), n) >= b)
                cancelBreeding(event, true);
            else if (ThreadLocalRandom.current().nextInt(0, 100) >= a)
                cancelBreeding(event, false);

    }

    @EventHandler
    public void eggThrowEvent(PlayerEggThrowEvent event) {
        if (checkArea(event.getPlayer(), EntityType.CHICKEN) >= plugin.getConfig().getInt("population-limits.chicken")) {
            event.setHatching(false);
            if (event.getPlayer() != null && !plugin.msg[1].equals(""))
                event.getPlayer().sendMessage(plugin.msg[1]);
        }
    }

    int checkArea(Entity ee, EntityType j) {
        int k = 0;
        for (Entity e : ee.getNearbyEntities(plugin.area[0], plugin.area[1], plugin.area[2]))
            if (e.getType() == j) k++;
        return k;
    }

    void cancelBreeding(EntityBreedEvent ebe, boolean one) {
        ebe.setExperience(0);
        ebe.getEntity().remove();
        if(ebe.getBreeder() != null && !plugin.msg[one ? 1 : 0].equals(""))
            ebe.getBreeder().sendMessage(plugin.msg[one ? 1 : 0]);
    }

}
