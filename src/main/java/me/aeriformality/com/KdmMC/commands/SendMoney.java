package me.aeriformality.com.KdmMC.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.aeriformality.com.KdmMC.KdmMC;
import me.aeriformality.com.KdmMC.profiles.StatsProfile;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class SendMoney extends BaseCommand {
    @CommandAlias("send")
    @CommandPermission("kdm.send")
    @Description("Send money to another player")

    public void onCommand(Player player, @Flags("other") Player target, @Name("amount") int amount) {
        KdmMC instance = KdmMC.getInstance();
        Map<UUID, StatsProfile> profiles = instance.getProfiles();

        if (!profiles.containsKey(player.getUniqueId()) || !profiles.containsKey(target.getUniqueId())) {
            player.sendMessage("Player not found!");
            return;
        }

        StatsProfile sender = profiles.get(player.getUniqueId());
        StatsProfile receiver = profiles.get(target.getUniqueId());

        if (sender.getMoney() < amount || amount < 0) {
            player.sendMessage("You don't have enough money!");
            return;
        }

        sender.removeMoney(amount);
        receiver.addMoney(amount);

        player.sendMessage("You sent " + amount + " to " + target.getName());
        target.sendMessage("You received " + amount + " from " + player.getName());
    }
}