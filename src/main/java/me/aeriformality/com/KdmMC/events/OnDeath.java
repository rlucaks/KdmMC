package me.aeriformality.com.KdmMC.events;

import me.aeriformality.com.KdmMC.KdmMC;
import me.aeriformality.com.KdmMC.profiles.StatsProfile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnDeath implements Listener {

    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent e) {
        KdmMC instance = KdmMC.getInstance();

        Player player = e.getPlayer();
        Player killer = player.getKiller();

        if (killer == null || !instance.getProfiles().containsKey(killer.getUniqueId())) return;
        if (!instance.getProfiles().containsKey(player.getUniqueId())) return;

        StatsProfile killerProfile = instance.getProfiles().get(killer.getUniqueId());
        killerProfile.addKill();
        killerProfile.addMoney(20);

        instance.getProfiles().get(player.getUniqueId()).addDeath();

        killer.sendMessage("You killed " + player.getName() + " and received 20 coins!");
        player.sendMessage("You were killed by " + killer.getName());
    }
}
