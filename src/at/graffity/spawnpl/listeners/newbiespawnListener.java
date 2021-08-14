package at.graffity.spawnpl.listeners;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class newbiespawnListener implements Listener {

	@EventHandler
	public void onNewbieSpawning(PlayerJoinEvent event) {

		Player player = event.getPlayer();

		if (player.hasPlayedBefore())
			return;
		File file = new File("plugins/SimpleSpawning/config.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

		if (!file.exists())
			return;
		
		if(cfg.getString("Newbiespawn.World") == null)
			return;
		
		World world = Bukkit.getWorld(cfg.getString("Newbiespawn.World"));
		double x = cfg.getDouble("Newbiespawn.X");
		double y = cfg.getDouble("Newbiepawn.Y");
		double z = cfg.getDouble("Newbiespawn.Z");
		float yaw = (float) cfg.getDouble("Newbiespawn.Yaw");
		float pitch = (float) cfg.getDouble("Newbiespawn.Pitch");
		Location location = new Location(world, x, y, z, yaw, pitch);
		player.teleport(location);
		

	}

}
