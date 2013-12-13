package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.location.CubedArea;

/**
 * 
 * @author JavaWarlord
 * @version 0.35
 * 
 *          v0.1 - Working NoSpawn area with constant values.
 * 
 *          v0.2 - Multiple NoSpawn areas
 * 
 *          v0.3 - Read areas from file
 * 
 *          v0.35 - Write areas to file
 * 
 *          v0.4 - Set boundaries of areas from inside game
 * 
 *          v0.5 - Delete areas
 * 
 *          v0.6 - View areas in game
 * 
 *          v0.7 - Resize areas
 * 
 *          v0.8 - Permissions
 * 
 */
public class NoSpawn extends JavaPlugin {

	// private List<CubedArea> areas;
	private AreaManager areaManager;

	public void onEnable() {
		// Save a copy of the default config.yml if one is not there
		// this.saveDefaultConfig();
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new MobSpawnListener(this), this);
		areaManager = new AreaManager(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("nospawn")
				|| cmd.getName().equalsIgnoreCase("nosp")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("reload")) {
					getServer().getPluginManager().disablePlugin(this);
					getServer().getPluginManager().enablePlugin(this);
					return true;
				} else if (args[0].equalsIgnoreCase("test")) {
					getServer().broadcastMessage(
							"This is just a test command!!!");
					return true;
				} else if (args[0].equalsIgnoreCase("show")) {
					areaManager.display(sender);
					return true;
				}
			} else if (args.length > 1) {
				if (args[0].equalsIgnoreCase("set")) {
					World world = getServer().getWorld("world");
					CubedArea newArea = new CubedArea(world,
							Integer.valueOf(args[1]), Integer.valueOf(args[2]),
							Integer.valueOf(args[3]), Integer.valueOf(args[4]),
							Integer.valueOf(args[5]), Integer.valueOf(args[6]));
					areaManager.add(newArea);
					sender.sendMessage("Added " + newArea);
					return true;
				}
			}
		}
		return false;
	}

	public void onDisable() {
		areaManager.save();
	}

	public void detectedSpawn(CreatureSpawnEvent event) {
		// TODO Auto-generated method stub
		if (areaManager.contains(event.getEntity().getLocation())) {
			event.setCancelled(true);
		}
	}
}
