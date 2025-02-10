package me.alanelol.pixelsitems.objetos;

import me.alanelol.pixelsitems.PixelsItems;
import me.alanelol.pixelsitems.interfaces.CustomItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ItemTest implements CustomItem {

    @Override
    public String GetName() {
        return "itemtest";
    }

    @Override
    public int GetQuantityRequired() {
        return 1;
    }

    @Override
    public long GetCooldown() {
        return 2L * 20L;
    }

    @Override
    public void AddToCooldown(Player Player, PixelsItems Plugin) {
        Plugin.getCooldown_ItemTest().add(Player.getUniqueId());
        new BukkitRunnable() {
            @Override
            public void run() {
                Plugin.getCooldown_ItemTest().remove(Player.getUniqueId());
            }
        }.runTaskLater(Plugin, GetCooldown());
    }

    @Override
    public String GetPermission() {
        return "pixels.items.itemtest";
    }

    @Override
    public void DoDamageAction(EntityDamageByEntityEvent Event, PixelsItems Plugin) {
        Player Player = (Player) Event.getDamager();
        Player Target = (Player) Event.getEntity();

        if (!Player.hasPermission(GetPermission()))
        {
            Player.sendMessage("Ⓢ No tienes permiso sorry.");
            return;
        }

        // CUSTOM ACTIONS
        Target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 2, true, true));
        Target.sendMessage("Ⓢ Has sido inflingido con SLOWNESS.");
        Player.sendMessage("Ⓢ Has inflingido a " + Target.getName() + " con SLOWNESS.");
    }

    @Override
    public void DoRightClickAction(PlayerInteractEvent Event, PixelsItems Plugin) {
        Player Player = Event.getPlayer();

        if (!Player.hasPermission(GetPermission()))
        {
            Player.sendMessage("Ⓢ No tienes permiso sorry.");
            return;
        }
        if (Plugin.getCooldown_ItemTest().contains(Player.getUniqueId()))
        {
            Player.sendMessage("Ⓢ Estás en cooldown");
            return;
        }

        AddToCooldown(Player, Plugin);

        // CUSTOM ACTIONS
        Player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2, true, true));
        Player.sendMessage("Ⓢ Has hecho click derecho.");
    }

    @Override
    public void DoHandSwapAction(PlayerSwapHandItemsEvent Event, PixelsItems Plugin) {
        Player Player = Event.getPlayer();

        if (!Player.hasPermission(GetPermission()))
        {
            Player.sendMessage("Ⓢ No tienes permiso sorry.");
            return;
        }

        // CUSTOM ACTIONS
        Player.setVelocity(new Vector(0, 5, 0));
        Player.sendMessage("Ⓢ Has cambiado de mano.");
    }

    @Override
    public void DoCrouchAction(PlayerToggleSneakEvent Event, PixelsItems Plugin) {
        Player Player = Event.getPlayer();

        if (!Player.hasPermission(GetPermission()))
        {
            Player.sendMessage("Ⓢ No tienes permiso sorry.");
            return;
        }

        // CUSTOM ACTIONS
        Player.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
        Player.sendMessage("Ⓢ Has shifteado! toma un diamante.");
    }
}
