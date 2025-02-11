package me.alanelol.pixelsitems.effects;

import com.github.fierioziy.particlenativeapi.api.ParticleNativeAPI;
import me.alanelol.pixelsitems.PixelsItems;
import me.alanelol.pixelsitems.interfaces.Effect;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TestEffect implements Effect {
    @Override
    public String GetName() {
        return "TestEffect";
    }

    @Override
    public String GetPermission() {return "pixelsitems.items.itemtest";}

    @Override
    public void DoEffect(Player Player, PixelsItems Plugin)
    {
        ParticleNativeAPI ParticleNativeAPI = Plugin.getParticleNativeAPI();
        Player.playSound(Player.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_7, 1f, 1f);
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i < 10)
                {
                    // Hacer cosas
                    ParticleNativeAPI.LIST_1_13.DUST
                            .color(Color.AQUA, 2)
                            .packet(true, Player.getLocation())
                            .sendTo(Bukkit.getOnlinePlayers());
                }
                else
                {
                    cancel();
                }

                i++;
            }
        }.runTaskTimer(Plugin, 0, 5);
    }
}
