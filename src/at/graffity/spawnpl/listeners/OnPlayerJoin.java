package at.graffity.spawnpl.listeners;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;

public class OnPlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!(player.hasPlayedBefore())) {

			File file = new File("plugins/SimpleSpawning/config.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

			if (cfg.getString("FirstJoinMessage").equalsIgnoreCase("") || cfg.getString("FirstJoinMessage") == null)
				return;

			String fjm = cfg.getString("FirstJoinMessage");

			String finalString = fjm.replaceAll("%player%", player.getName())
					.replaceAll("%customname%", player.getCustomName()).replaceAll("&6", "§6").replaceAll("&c", "§c")
					.replaceAll("&4", "§4").replaceAll("&e", "§e").replaceAll("&2", "§2").replaceAll("&a", "&4")
					.replaceAll("&b", "§b").replaceAll("&3", "§3").replaceAll("&1", "§1").replaceAll("&9", "§9")
					.replaceAll("&d", "§d").replaceAll("&5", "§5").replaceAll("&f", "§f").replaceAll("&7", "&7")
					.replaceAll("&8", "§8").replaceAll("&0", "§0").replaceAll("&r", "§r").replaceAll("§l", "§l")
					.replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("%m", "§m").replaceAll("&n", "§n");
            if(Bukkit.getPluginManager().getPlugin("PlaceholderApi") != null) {
			finalString = PlaceholderAPI.setPlaceholders(player, finalString);
            }
			event.setJoinMessage(finalString);

		} else {

			File file = new File("plugins/SimpleSpawning/config.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

			if (cfg.getString("JoinMessage").equalsIgnoreCase("") || cfg.getString("JoinMessage") == null)
				return;

			String jm = cfg.getString("JoinMessage");

			String finalString = jm.replaceAll("%player%", player.getName())
					.replaceAll("%customname%", player.getCustomName()).replaceAll("&6", "§6").replaceAll("&c", "§c")
					.replaceAll("&4", "§4").replaceAll("&e", "§e").replaceAll("&2", "§2").replaceAll("&a", "&4")
					.replaceAll("&b", "§b").replaceAll("&3", "§3").replaceAll("&1", "§1").replaceAll("&9", "§9")
					.replaceAll("&d", "§d").replaceAll("&5", "§5").replaceAll("&f", "§f").replaceAll("&7", "&7")
					.replaceAll("&8", "§8").replaceAll("&0", "§0").replaceAll("&r", "§r").replaceAll("§l", "§l")
					.replaceAll("&o", "§o").replaceAll("&k", "§k").replaceAll("%m", "§m").replaceAll("&n", "§n");
            if(Bukkit.getPluginManager().getPlugin("PlaceholderApi") != null) {

			finalString = PlaceholderAPI.setPlaceholders(player, finalString);
            }
			event.setJoinMessage(finalString);

		}

	}

}
