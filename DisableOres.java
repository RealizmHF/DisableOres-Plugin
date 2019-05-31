package io.github.RealizmHF.DisableOres;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DisableOres extends JavaPlugin {
	
	@Override
	public void onEnable(){
		
		createConfig();
		
		@SuppressWarnings("unused")
		BreakOre brkOre = new BreakOre(this);
		
		this.getCommand("disableores").setExecutor(new OresCommands(this));
	}
	
	@Override
	public void onDisable() {
		
		this.saveConfig();
	}
	
	private void createConfig() {
		
		try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");
                this.saveConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
		
		FileConfiguration c = this.getConfig();
		
		c.options().header("Disable Ores Config: \n");
		c.addDefault("world", true);

		c.addDefault("COAL_ORE", true);
		c.addDefault("IRON_ORE", true);
		c.addDefault("GOLD_ORE", true);
		c.addDefault("DIAMOND_ORE", true);
		c.addDefault("REDSTONE_ORE", true);
		c.addDefault("EMERALD_ORE", true);
		c.addDefault("LAPIS_ORE", true);
		
		c.addDefault("message", "Sorry, this block has been disabled for this world");
		
		c.options().copyDefaults(true);
		saveConfig();
		reloadConfig();
	}
}
