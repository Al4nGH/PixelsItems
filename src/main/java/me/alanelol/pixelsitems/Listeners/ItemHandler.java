package me.alanelol.pixelsitems.Listeners;

import me.alanelol.pixelsitems.PixelsItems;
import me.alanelol.pixelsitems.objetos.ItemTest;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
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

    // ItemList
    ItemTest ItemTest = new ItemTest();

    public ItemHandler(PixelsItems plugin) {
        Plugin = plugin; // Inicializar plugin
    }

    //DoDamageAction
    @EventHandler
    public void OnDamage(EntityDamageByEntityEvent Event)
    {
        if (Event.getDamager().getType() == EntityType.PLAYER && // Damager = un Player?
                Event.getEntity().getType() == EntityType.PLAYER) // Victim = un Player?
        {
            String ItemName = GetItemName(Event); // Nombre del item en formato MiniMessage
            if (ItemName.isEmpty()) {return;} // NULL CHECK

            // ItemList
            if (ItemName.equals(ItemTest.GetName())) {
                ItemTest.DoDamageAction(Event, Plugin);
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

            // ItemList
            if (ItemName.equals(ItemTest.GetName())) {
                ItemTest.DoRightClickAction(Event, Plugin);
            }
        }
    }

    //DoHandSwapAction
    @EventHandler
    public void OnItemSwap(PlayerSwapHandItemsEvent Event)
    {
        String ItemName = GetItemName(Event); // Nombre del item en formato MiniMessage
        if (ItemName.isEmpty()) {return;} // NULL CHECK

        // ItemList
        if (ItemName.equals(ItemTest.GetName())) {
            ItemTest.DoHandSwapAction(Event, Plugin);
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
            if (ItemName.equals(ItemTest.GetName())) {
                ItemTest.DoCrouchAction(Event, Plugin);
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
}
