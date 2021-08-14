package at.graffity.spawnpl.listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class AddPlayerItemsAtFirstJoin implements Listener {

	@SuppressWarnings("unchecked")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
		File f = new File("plugins/SimpleSpawning/config.yml");
		YamlConfiguration scfg = YamlConfiguration.loadConfiguration(f);

		Player player = event.getPlayer();

		if (scfg.getString("UseSpawnkits").equals("false"))
			return;

		if (scfg.getString("UseSpawnkits").equalsIgnoreCase("daily")) {
			File file2 = new File("plugins/SimpleSpawning/spawnkits.yml");
			YamlConfiguration kcfg = YamlConfiguration.loadConfiguration(file2);

			if (!file2.exists())
				return;
			Path p = Paths.get("plugins/SimpleSpawning/Joiners");

			if (!Files.exists(p)) {
				try {
					Files.createDirectory(p);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			File file = new File("plugins/SimpleSpawning/Joiners/" + player.getUniqueId().toString() + ".yml");
			if (file.exists()) {
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				if (!cfg.getString("GotItems").equalsIgnoreCase(LocalDate.now().toString())) {

					ItemStack[] content = ((List<ItemStack>) kcfg.get("inventory.armor")).toArray(new ItemStack[0]);
					player.getInventory().setArmorContents(content);
					content = ((List<ItemStack>) kcfg.get("inventory.content")).toArray(new ItemStack[0]);
					player.getInventory().setContents(content);
					cfg.set("GotItems", LocalDate.now().toString());
					cfg.save(file);

				}

			} else {
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				cfg.set("GotItems", LocalDate.now().toString());
				@SuppressWarnings("unchecked")
				ItemStack[] content = ((List<ItemStack>) kcfg.get("inventory.armor")).toArray(new ItemStack[0]);
				player.getInventory().setArmorContents(content);
				content = ((List<ItemStack>) kcfg.get("inventory.content")).toArray(new ItemStack[0]);
				player.getInventory().setContents(content);
				cfg.save(file);

			}
		}

		if (scfg.getString("UseSpawnkits").equals("always")) {

			File file = new File("plugins/SimpleSpawning/spawnkits.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			@SuppressWarnings("unchecked")
			ItemStack[] content = ((List<ItemStack>) cfg.get("inventory.armor")).toArray(new ItemStack[0]);
			player.getInventory().setArmorContents(content);
			content = ((List<ItemStack>) cfg.get("inventory.content")).toArray(new ItemStack[0]);
			player.getInventory().setContents(content);

		}
		if (scfg.getString("UseSpawnkits").equalsIgnoreCase("once")) {
			if (!(player.hasPlayedBefore())) {

				File file = new File("plugins/SimpleSpawning/spawnkits.yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				@SuppressWarnings("unchecked")
				ItemStack[] content = ((List<ItemStack>) cfg.get("inventory.armor")).toArray(new ItemStack[0]);
				player.getInventory().setArmorContents(content);
				content = ((List<ItemStack>) cfg.get("inventory.content")).toArray(new ItemStack[0]);
				player.getInventory().setContents(content);

			}
		}
	}

}
