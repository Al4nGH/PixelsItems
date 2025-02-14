package me.alanelol.pixelsitems.listeners;

import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
import me.alanelol.pixelsitems.PixelsItems;
import me.alanelol.pixelsitems.custom_items.CustomItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class ItemHandler implements Listener {
    private final PixelsItems Plugin; // Plugin
    MiniMessage miniMessage = MiniMessage.miniMessage(); // MiniMessage

    public ItemHandler(PixelsItems plugin) {
        Plugin = plugin; // Inicializar plugin
    }

    //DoDamageAction
    @EventHandler
    public void OnDamage(EntityDamageByEntityEvent Event)
    {
        Bukkit.getLogger().info("Damager: " + Event.getDamager().getType().toString());
        Bukkit.getLogger().info("Entity: " + Event.getEntity().getType().toString());
        if (Event.getDamager().getType() == EntityType.PLAYER && // Damager = un Player?
                Event.getEntity().getType() == EntityType.PLAYER) // Victim = un Player?
        {
            String ItemName = GetItemName(Event); // Nombre del item en formato MiniMessage
            if (ItemName.isEmpty()) {return;} // NULL CHECK

            // ItemList (NUEVO METODO QUE ME VA A SALVAR AÑOS ES MUY GOD EN SERIO)
            for (CustomItem CustomItem : Plugin.getItemList())
            {
                if (ItemName.equals(CustomItem.GetName()))
                {
                    CustomItem.DoDamageAction(Event, Plugin);
                }
            }
        }
    }

    //DoRightClickAction
    @EventHandler
    public void OnInteract(PlayerInteractEvent Event)
    {
        if (Event.getAction().isRightClick()) // Es click derecho?
        {
            String ItemName = GetItemName(Event); // Nombre del item en formato MiniMessage
            if (ItemName.isEmpty()) {return;} // NULL CHECK

            // ItemList (me encanta este metodo)
            for (CustomItem CustomItem : Plugin.getItemList())
            {
                if (ItemName.equals(CustomItem.GetName()))
                {
                    CustomItem.DoRightClickAction(Event, Plugin);
                }
            }
        }
    }

    //DoHandSwapAction
    @EventHandler
    public void OnItemSwap(PlayerSwapHandItemsEvent Event)
    {
        String ItemName = GetItemName(Event); // Nombre del item en formato MiniMessage
        if (ItemName.isEmpty()) {return;} // NULL CHECK

        // ItemList (no sabia q se podia hacer :v)
        for (CustomItem CustomItem : Plugin.getItemList())
        {
            if (ItemName.equals(CustomItem.GetName()))
            {
                CustomItem.DoHandSwapAction(Event, Plugin);
            }
        }
    }

    //DoCrouchAction
    @EventHandler
    public void OnSneakToggle(PlayerToggleSneakEvent Event)
    {
        if (Event.isSneaking()) // Está agachado? (DESPUES DE TOGGLEAR)
        {
            if (Event.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null) {return;}
            String ItemName = GetItemName(Event); // Nombre del item en formato MiniMessage
            if (ItemName.isEmpty()) {return;} // NULL CHECK

            // ItemList
            for (CustomItem CustomItem : Plugin.getItemList())
            {
                if (ItemName.equals(CustomItem.GetName()))
                {
                    CustomItem.DoCrouchAction(Event, Plugin);
                }
            }
        }
    }

    //DoProjectileLaunchAction
    @EventHandler
    public void OnProjectileLaunch(PlayerLaunchProjectileEvent Event)
    {
        if (Event.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null) {return;}
        String ItemName = GetItemName(Event);
        if (ItemName.isEmpty()) {return;}

        // ItemList
        for (CustomItem CustomItem : Plugin.getItemList())
        {
            if (ItemName.equals(CustomItem.GetName()))
            {
                CustomItem.DoProjectileLaunchAction(Event, Plugin);
            }
        }
    }

    private String GetItemName(EntityDamageByEntityEvent Event)
    {
        Player Player = (Player) Event.getDamager();
        if (Player.getInventory().getItemInMainHand().getItemMeta() == null)
        {
            return "";
        }
        Component HANDItemComponent = Player.getInventory().getItemInMainHand().getItemMeta().displayName();
        return (HANDItemComponent != null) ? miniMessage.serialize(HANDItemComponent) : "";
    }

    private String GetItemName(PlayerInteractEvent Event)
    {
        if (Event.getItem() == null || Event.getItem().getItemMeta() == null)
        {
            return "";
        }
        Component HANDItemComponent = Event.getItem().getItemMeta().displayName();
        return (HANDItemComponent != null) ? miniMessage.serialize(HANDItemComponent) : "";
    }

    private String GetItemName(PlayerSwapHandItemsEvent Event)
    {
        if (Event.getOffHandItem().getItemMeta() == null)
        {
            return "";
        }
        Component HANDItemComponent = Event.getOffHandItem().getItemMeta().displayName();
        return (HANDItemComponent != null) ? miniMessage.serialize(HANDItemComponent) : "";
    }

    private String GetItemName(PlayerToggleSneakEvent Event)
    {
        Player Player = Event.getPlayer();
        if (Player.getInventory().getItemInMainHand().getItemMeta() == null)
        {
            return "";
        }
        Component HANDItemComponent = Player.getInventory().getItemInMainHand().getItemMeta().displayName();

        return (HANDItemComponent != null) ? miniMessage.serialize(HANDItemComponent) : "";
    }

    private String GetItemName(PlayerLaunchProjectileEvent Event)
    {
        Player Player = Event.getPlayer();
        if (Player.getInventory().getItemInMainHand().getItemMeta() == null)
        {
            return "";
        }
        Component HANDItemComponent = Player.getInventory().getItemInMainHand().getItemMeta().displayName();

        return (HANDItemComponent != null) ? miniMessage.serialize(HANDItemComponent) : "";
    }
}
