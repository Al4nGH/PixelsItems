package me.alanelol.pixelsitems.custom_items.items;

import me.alanelol.pixelsitems.PixelsItems;
import me.alanelol.pixelsitems.custom_items.CustomItem;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class ArcoRegenerador implements CustomItem {
    @Override
    public String GetName() {
        return "Arco Regenerador";
    }

    @Override
    public ItemStack GetItemStack() {
        ItemStack ItemStack = new ItemStack(Material.BOW);
        ItemMeta ItemMeta = ItemStack.getItemMeta();
        ItemMeta.displayName(MiniMessage.miniMessage().deserialize(GetName()));
        ItemStack.setItemMeta(ItemMeta);
        return ItemStack;
    }

    @Override
    public int GetQuantityRequired() {
        return 1;
    }

    @Override
    public long GetCooldown() {
        return 120L * 20L;
    }

    @Override
    public void AddToCooldown(Player Player, PixelsItems Plugin) {
        Plugin.getCooldown_ArcoRegenerador().add(Player.getUniqueId());
        new BukkitRunnable() {
            @Override
            public void run() {
                Plugin.getCooldown_ArcoRegenerador().remove(Player.getUniqueId());
            }
        }.runTaskLater(Plugin, GetCooldown());
    }

    @Override
    public String GetPermission() {
        return "pixelsitems.items.arcoregenerador";
    }

    @Override
    public void DoDamageAction(EntityDamageByEntityEvent Event, PixelsItems Plugin)
    {
        Player Player = (Player) Event.getDamager();
        Player Target = (Player) Event.getEntity();

        if (!Player.hasPermission(GetPermission()))
        {
            Player.sendMessage("Ⓢ No tienes permiso sorry.");
            return;
        }

    }
}
