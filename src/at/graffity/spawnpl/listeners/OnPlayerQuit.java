package at.graffity.spawnpl.listeners;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.clip.placeholderapi.PlaceholderAPI;

public class OnPlayerQuit implements Listener{
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		File file = new File("plugins/SimpleSpawning/config.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

		if (cfg.getString("QuitMessage").equalsIgnoreCase("")|| cfg.getString("QuitMessage") == null)
			return;
		
	        
	        
		
        String qm = cfg.getString("QuitMessage");



        String finalString = qm
                .replaceAll("%player%", player.getName())
                .replaceAll("%customname%", player.getCustomName())
                .replaceAll("&6", "§6")
                .replaceAll("&c", "§c")
                .replaceAll("&4", "§4")
                .replaceAll("&e", "§e")
                .replaceAll("&2", "§2")
                .replaceAll("&a", "&4")
                .replaceAll("&b", "§b")
                .replaceAll("&3", "§3")
                .replaceAll("&1", "§1")
                .replaceAll("&9", "§9")
                .replaceAll("&d", "§d")
                .replaceAll("&5", "§5")
                .replaceAll("&f", "§f")
                .replaceAll("&7", "&7")
                .replaceAll("&8", "§8")
                .replaceAll("&0", "§0")
                .replaceAll("&r", "§r")
                .replaceAll("§l", "§l")
                .replaceAll("&o", "§o")
                .replaceAll("&k", "§k")
                .replaceAll("%m", "§m")
                .replaceAll("&n", "§n");

        


        if(Bukkit.getPluginManager().getPlugin("PlaceholderApi") != null) {
        finalString = PlaceholderAPI.setPlaceholders(player, finalString);
        }

        
        event.setQuitMessage(finalString);
        

	}
		
		
		
		
		
		
		
		
	}
	
	
	
	
	


