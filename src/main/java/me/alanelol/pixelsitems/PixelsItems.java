package me.alanelol.pixelsitems;

import com.github.fierioziy.particlenativeapi.api.ParticleNativeAPI;
import com.github.fierioziy.particlenativeapi.core.ParticleNativeCore;
import me.alanelol.pixelsitems.custom_items.CustomItem;
import me.alanelol.pixelsitems.custom_items.items.ItemTest;
import me.alanelol.pixelsitems.listeners.ItemHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class PixelsItems extends JavaPlugin {

    // ItemList
    ItemTest ItemTest = new ItemTest();
    private final List<CustomItem> ItemList = List.of(new ItemTest());
    private final ArrayList<UUID> Cooldown_ItemTest = new ArrayList<>();
    private final ArrayList<UUID> Cooldown_ArcoRegenerador = new ArrayList<>();
    private final ParticleNativeAPI ParticleNativeAPI = ParticleNativeCore.loadAPI(this);

    @Override @SuppressWarnings("UnstableApiUsage")
    public void onEnable() {
        // Plugin startup logic

        getServer().getLogger().info("-----------------------");
        getServer().getLogger().info("");
        getServer().getLogger().info("");
        getServer().getLogger().info("PIXELSITEMS INICIADO XD");
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

    // GETTERS
    public ArrayList<UUID> getCooldown_ItemTest() {
        return Cooldown_ItemTest;
    }

    public ParticleNativeAPI getParticleNativeAPI() {
        return ParticleNativeAPI;
    }

    public ArrayList<UUID> getCooldown_ArcoRegenerador() {
        return Cooldown_ArcoRegenerador;
    }

    public List<CustomItem> getItemList() {
        return ItemList;
    }
}
