package me.alanelol.pixelsitems.custom_items;

import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
import me.alanelol.pixelsitems.PixelsItems;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

public interface CustomItem {
    // Nombre del del item
    String GetName();

    // El item en si
    ItemStack GetItemStack();

    // Cantidad requerida
    int GetQuantityRequired();

    // Cooldown para acciones
    long GetCooldown();

    // Agregalo al cooldown pues
    void AddToCooldown(Player Player, PixelsItems Plugin);

    // Permiso
    String GetPermission();

    // Al golpear a alguien
    default void DoDamageAction(EntityDamageByEntityEvent Event, PixelsItems Plugin) {};

    // Al hacer click izq
    default void DoRightClickAction(PlayerInteractEvent Event, PixelsItems Plugin) {};

    // Al hacer hand-swap
    default void DoHandSwapAction(PlayerSwapHandItemsEvent Event, PixelsItems Plugin) {};

    // Al agacharse
    default void DoCrouchAction(PlayerToggleSneakEvent Event, PixelsItems Plugin) {};

    // Al DISPARAR el projectil (no golpear)
    default void DoProjectileLaunchAction(PlayerLaunchProjectileEvent Event, PixelsItems Plugin) {}
}
