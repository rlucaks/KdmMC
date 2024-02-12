package me.aeriformality.com.KdmMC.events;

import me.aeriformality.com.KdmMC.KdmMC;
import me.aeriformality.com.KdmMC.profiles.StatsProfile;
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
            profiles.put(playerId, new StatsProfile());
        }
    }
}
