package me.aeriformality.com.KdmMC.profiles;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class StatsProfile implements ConfigurationSerializable {
    private int kills, deaths, money;

    public void addKill() { kills++; }
    public void addDeath() { deaths++; }
    public void addMoney(int amount) { money += amount; }
    public void removeMoney(int amount) { money -= amount; }
    public void reset() { kills = deaths = money = 0; }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("kills", this.kills);
        map.put("deaths", this.deaths);
        map.put("money", this.money);
        return map;
    }

    public static StatsProfile deserialize(Map<String, Object> map) {
        StatsProfile profile = new StatsProfile();
        profile.kills = (int) map.get("kills");
        profile.deaths = (int) map.get("deaths");
        profile.money = (int) map.get("money");
        return profile;
    }
}
