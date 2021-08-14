package at.graffity.spawnpl.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				if (player.hasPermission("sspawning.setnewbiespawn") || player.hasPermission("sspawning.admin")) {
					File file = new File("plugins/SimpleSpawning/config.yml");
					if (file.exists()) {
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
						if (cfg.getBoolean("Newbiespawn")) {
							cfg.set("Newbiespawn.world", player.getWorld().getName());
							cfg.set("Newbiespawn.X", player.getLocation().getBlockX());
							cfg.set("Newbiespawn.Y", player.getLocation().getBlockY());
							cfg.set("Newbiespawn.Z", player.getLocation().getBlockZ());
							cfg.set("Newbiespawn.Yaw", player.getLocation().getYaw());
							cfg.set("Newbiespawn.Pitch", player.getLocation().getPitch());
							player.sendMessage("§7Locatian has been saved to config!");
							try {
								cfg.save(file);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							player.sendMessage("§cNewbiespawn isn't enabled in config");
						}

					} else {
						player.sendMessage("§cCan't find the config. Is everything alright?");
					}

				} else {
					player.sendMessage("§c You don't have the needed Permission");
				}

			} else {
				player.sendMessage("§cToo many arguments! Please use /setnewbiespawn!");
			}

		} else {
			sender.sendMessage("§cYou have to be a player to execute this command!");
		}

		// TODO Auto-generated method stub
		return false;
	}

}
