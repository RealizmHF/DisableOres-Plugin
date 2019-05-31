package io.github.RealizmHF.DisableOres;

import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class OresCommands implements CommandExecutor {
	
	private DisableOres plugin;

	public OresCommands(DisableOres disableOres) {
		this.plugin = disableOres;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length > 0) {
			
			//If command is to add a block to config
			if(args[0].equalsIgnoreCase("add") && sender.hasPermission("disableores.add")) {
				
				//If args length is 1
				if(args.length == 2) {
					

					Object configResults = this.plugin.getConfig().get(args[1]);
					
					try{
						
						//If block isn't being disabled, disable it
						if(configResults.toString().equalsIgnoreCase("false")) {
							sender.sendMessage(args[1] + " has been disabled");
							this.plugin.getConfig().set(args[1], true);
							this.plugin.getConfig().options().copyDefaults(true);
							this.plugin.saveConfig();
							this.plugin.reloadConfig();
						}
						else if(configResults.toString().equalsIgnoreCase("true")){
							sender.sendMessage(args[1] + " is already disabled.");
						}
						
					}
					catch(CommandException e) {
						
						//Else, config doesn't contain the block and must have one added
						
						sender.sendMessage(args[1] + " has been disabled");
						this.plugin.getConfig().addDefault(args[1], true);
						this.plugin.getConfig().options().copyDefaults(true);
						this.plugin.saveConfig();
						this.plugin.reloadConfig();
					}
					
				}
				//Else, send error message
				else {
					sender.sendMessage("Invalid number of arguments. \nUse /disableores add [block_name]");
				}
			}
			
			//If command is to remove a block from config
			else if(args[0].equalsIgnoreCase("remove") && sender.hasPermission("disableores.remove")) {
				
				//If args length is 1
				if(args.length == 2) {
					
					Object configResults = this.plugin.getConfig().get(args[1]);
					
					//If config contains block to be removed
					if(configResults.toString().equalsIgnoreCase("true")) {
						
						sender.sendMessage(args[1] + " has been re-enabled");
						this.plugin.getConfig().set(args[1], false);
						this.plugin.getConfig().options().copyDefaults(true);
						this.plugin.saveConfig();
						this.plugin.reloadConfig();
					}
					else {
						sender.sendMessage(args[1] + "couldn't be found.");
					}
				}
				else {
					sender.sendMessage("Invalid number of arguments. \nUse /disableores remove [block_name]");
				}
			}
			else {
				sender.sendMessage("Invalid command. \nUse /disableores [add/remove] [block_name]");
			}
		}
		else {
			sender.sendMessage("Invalid number of arguments. \nUse /disableores [add/remove] [block_name]");
		}

		return false;
	}


}
