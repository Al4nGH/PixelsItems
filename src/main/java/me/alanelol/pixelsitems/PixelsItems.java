package me.alanelol.pixelsitems;

import me.alanelol.pixelsitems.Listeners.ItemHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public final class PixelsItems extends JavaPlugin {

    private final ArrayList<UUID> Cooldown_ItemTest = new ArrayList<>();

    @Override @SuppressWarnings("UnstableApiUsage")
    public void onEnable() {
        // Plugin startup logic
        getServer().getLogger().info("-----------------------");
        getServer().getLogger().info("");
        getServer().getLogger().info("");
        getServer().getLogger().info("PIXELSITEMS INICIADO LOL XD");
        getServer().getLogger().info("");
        getServer().getLogger().info("");
        getServer().getLogger().info("-----------------------");
        getServer().getPluginManager().registerEvents(new ItemHandler(this), this);
    }

    @Override @SuppressWarnings("UnstableApiUsage")
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getLogger().info("-----------------------");
        getServer().getLogger().info("");
        getServer().getLogger().info("");
        getServer().getLogger().info("ADIOSSSS SOSS");
        getServer().getLogger().info("");
        getServer().getLogger().info("");
        getServer().getLogger().info("-----------------------");
    }

    public ArrayList<UUID> getCooldown_ItemTest() {
        return Cooldown_ItemTest;
    }
}
