package at.graffity.spawnpl.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import at.graffity.spawnpl.commands.SetSpawn;
import at.graffity.spawnpl.commands.save;
import at.graffity.spawnpl.commands.setfirstjoinmsg;
import at.graffity.spawnpl.commands.setjoinmsg;
import at.graffity.spawnpl.commands.setquitmsg;
import at.graffity.spawnpl.listeners.AddPlayerItemsAtFirstJoin;
import at.graffity.spawnpl.listeners.OnPlayerJoin;
import at.graffity.spawnpl.listeners.OnPlayerQuit;
import at.graffity.spawnpl.listeners.SetFirstJoinMsgListener;
import at.graffity.spawnpl.listeners.SetQuitMsgListener;
import at.graffity.spawnpl.listeners.setJoinMsgListener;
import at.graffity.spawnpl.main.Main;

public class Main extends JavaPlugin {

	private static Main plugin;

	public void onEnable() {
		plugin = this;
		System.out.println("The Plugin was started succesfully!");
		
		
		getCommand("setfirstjoinmsg").setExecutor(new setfirstjoinmsg());
		getCommand("setjoinmsg").setExecutor(new setjoinmsg());
		getCommand("setquitmsg").setExecutor(new setquitmsg());
		getCommand("setnewbiespawn").setExecutor(new SetSpawn());
		getCommand("setkits").setExecutor(new save());
	
		
		PluginManager pluginManager = Bukkit.getPluginManager();
		
		
		pluginManager.registerEvents(new OnPlayerJoin(), this);
		pluginManager.registerEvents(new setJoinMsgListener(), this);
		pluginManager.registerEvents(new SetFirstJoinMsgListener(), this);
		pluginManager.registerEvents(new OnPlayerQuit(), this);
		pluginManager.registerEvents(new SetQuitMsgListener(), this);
		pluginManager.registerEvents(new AddPlayerItemsAtFirstJoin(), this);
		
		if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			System.out.println("PlaceholderAPI was found and hooked in succesfully!");
			
		} else {
			System.out.println("§cPlaceholderAPI wasn't found. No Placeholders avaible");
		}

		Path p = Paths.get("plugins/SimpleSpawning");
		File file = new File("plugins/SimpleSpawning/config.yml");
		if (!Files.exists(p)) {
			try {
				Files.createDirectory(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			cfg.set("Plugin", "SimpleSpawning");
			cfg.set("JoinMessage", "");
			cfg.set("FirstJoinMessage", "");
			cfg.set("QuitMessage", "");
			cfg.set("#", "Set this to always if you wanna overvrite the player inventory at every join.");
			cfg.set("##", "You can also use daily, that a player gets his rewards only one a day!");
			cfg.set("###", "Or to once if you wanna give items only at firstjoin or to false if you don't wanna use that feature at all.");
			cfg.set("UseSpawnkits", "false");
			try {
				cfg.save(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void onDisable() {
		System.out.println("The Plugin was deactivated succesfully!");
	}

	public static Main getPlugin() {
		return plugin;
	}

}