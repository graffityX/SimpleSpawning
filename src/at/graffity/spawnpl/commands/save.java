package at.graffity.spawnpl.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class save implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if (s instanceof Player) {
			Player player = (Player) s;
			if (player.hasPermission("sspawning.setkits") || player.hasPermission("sspawning.admin")) {
				if (args.length == 0) {
					File f = new File("plugins/SimpleSpawning/spawnkits.yml");
					YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

					cfg.set("inventory.armor", player.getInventory().getArmorContents());
					cfg.set("inventory.content", player.getInventory().getContents());
					player.sendMessage("§eThe kits were succesfully saved to config!");
					try {
						cfg.save(f);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}

				} else {
					player.sendMessage("§cToo many arguments! Please use /setkits");
				}

			} else {
				player.sendMessage("§cYou don't have the needed permission to do this!");
			}

		} else {
			s.sendMessage("§cYou have to be player to execute this command!");
		}

		// TODO Auto-generated method stub
		return false;
	}

}
