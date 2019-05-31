package io.github.RealizmHF.DisableOres;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakOre implements Listener{
	
	DisableOres plugin;
	
	public BreakOre(DisableOres disableOres) {
		this.plugin = disableOres;
		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
		//If event is in selected world(s)
		if(plugin.getConfig().getString(event.getPlayer().getWorld().getName()) != null) {

			event.getPlayer().sendMessage(event.getBlock().getType().toString());
			//If the config file contains the block being broke, break block without drop and send message
			if(plugin.getConfig().getBoolean(event.getBlock().getType().toString())){
				
				event.setDropItems(false);
				event.setExpToDrop(0);
				event.getPlayer().sendMessage(plugin.getConfig().getString("message"));
			}
		}
	}

}
