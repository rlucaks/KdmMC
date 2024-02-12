package me.aeriformity.com.KdmMC.events;

import me.aeriformity.com.KdmMC.KdmMC;
import me.aeriformity.com.KdmMC.profiles.StatsProfile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class OnJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        HashMap<UUID, StatsProfile> profiles = KdmMC.getInstance().getProfiles();
        UUID playerId = e.getPlayer().getUniqueId();

        if (!profiles.containsKey(playerId)) {
            System.out.println("Fired");
            profiles.put(playerId, new StatsProfile());
        }
    }
}
