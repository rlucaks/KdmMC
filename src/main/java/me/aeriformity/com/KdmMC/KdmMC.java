package me.aeriformity.com.KdmMC;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import me.aeriformity.com.KdmMC.commands.SendMoney;
import me.aeriformity.com.KdmMC.commands.Stats;
import me.aeriformity.com.KdmMC.events.OnDeath;
import me.aeriformity.com.KdmMC.events.OnJoin;
import me.aeriformity.com.KdmMC.profiles.StatsProfile;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

@Getter
public final class KdmMC extends JavaPlugin {

    @Getter
    private static KdmMC instance;
    private final HashMap<UUID, StatsProfile> profiles = new HashMap<>();


    @Override
    public void onEnable() {
        logInfo("KdmMC is initialising...");

        instance = this;

        saveConfig();
        loadProfiles();

        registerCommands();
        registerEvents();

        logInfo("KdmMC is enabled.");
    }

    @Override
    public void onDisable() {
        logInfo("KdmMC is disabling...");
        saveProfiles();
        logInfo("KdmMC is disabled.");
    }

    private void registerCommands() {
        PaperCommandManager manager = new PaperCommandManager(this);

        manager.registerCommand(new SendMoney());
        manager.registerCommand(new Stats());
        manager.registerCommand(new SendMoney());
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new OnDeath(), this);
        pm.registerEvents(new OnJoin(), this);
    }

    private void saveProfiles() {
        logInfo("Saving profiles...");
        for (Map.Entry<UUID, StatsProfile> entry : profiles.entrySet()) {
            getConfig().set("stats." + entry.getKey().toString(), entry.getValue().serialize());
        }
        saveConfig();
    }

    private void loadProfiles() {
        ConfigurationSection section = getConfig().getConfigurationSection("stats");
        if (section != null) {
            section.getKeys(false).forEach(key -> {
                StatsProfile profile = StatsProfile.deserialize(getConfig().getConfigurationSection("stats." + key).getValues(false));
                UUID uuid = UUID.fromString(key);
                profiles.put(uuid, profile);
            });
        }
    }

    private void logInfo(String message) {
        getLogger().log(Level.INFO, message);
    }
}
