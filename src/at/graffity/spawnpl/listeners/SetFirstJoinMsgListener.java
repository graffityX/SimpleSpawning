package at.graffity.spawnpl.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import at.graffity.spawnpl.commands.setfirstjoinmsg;

public class SetFirstJoinMsgListener implements Listener{
	
	@EventHandler
	public void onSetten(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if(setfirstjoinmsg.msgSetFirstArray.contains(player.getName())) {
			String msg = event.getMessage();
			event.setCancelled(true);
			File file = new File("plugins/SimpleSpawning/config.yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			cfg.set("FirstJoinMessage", msg.toString());
			player.sendMessage("§7You succesfully changed the first message for joining Players");
			setfirstjoinmsg.msgSetFirstArray.remove(player.getName());
			try {
				cfg.save(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
	
		
			
		}
		
		
		
		}
		
		
		
	}
	

}
