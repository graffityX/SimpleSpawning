package at.graffity.spawnpl.commands;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setquitmsg implements CommandExecutor {

	public static ArrayList<String> qmsgSetArray = new ArrayList<String>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				if (player.hasPermission("sspawning.setquitmsg") || player.hasPermission("sspawning.admin")) {
					File file = new File("plugins/SimpleSpawning/config.yml");
					if (file.exists()) {
						player.sendMessage(
								"§7Input the text you wanna use as quit message! Use %player% for the player's name!");
						qmsgSetArray.add(player.getName());

					} else {
						player.sendMessage("§cCan't find the config. Is everything alright?");
					}

				} else {
					player.sendMessage("§c You don't have the needed Permission");
				}

			} else {
				player.sendMessage("§cToo many arguments! Please use /setquitmsg!");
			}

		} else {
			sender.sendMessage("§cYou have to be a player to execute this command!");
		}

		// TODO Auto-generated method stub
		return false;
	}

}
