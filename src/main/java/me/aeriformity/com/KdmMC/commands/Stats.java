package me.aeriformity.com.KdmMC.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.aeriformity.com.KdmMC.KdmMC;
import me.aeriformity.com.KdmMC.profiles.StatsProfile;
import org.bukkit.entity.Player;

public class Stats extends BaseCommand {

    @CommandAlias("stats")
    @CommandPermission("kdm.stats")
    @Description("Check your stats or someone else's stats.")

    public void onCommand(Player player, @Optional @Flags("other") Player target) {
        Player p = target == null ? player : target;
        StatsProfile stats = KdmMC.getInstance().getProfiles().get(p.getUniqueId());

        if (stats == null) {
            player.sendMessage("Player not found!");
            return;
        }

        player.sendMessage(String.format("%s has %d kill(s) and %d death(s)! And %d coin(s)!", p.getName(), stats.getKills(), stats.getDeaths(), stats.getMoney()));
    }
}
