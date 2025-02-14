package me.alanelol.pixelsitems.custom_effects;

import me.alanelol.pixelsitems.PixelsItems;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface Effect {
    String GetName();

    String GetPermission();

    default void DoEffect(Player Player, PixelsItems Plugin) {};
    default void DoEffect(Block Block, PixelsItems Plugin) {};
    default void DoEffect(Location Location, PixelsItems Plugin) {};
    default void DoEffect(Entity Entity, PixelsItems Plugin) {};
}
