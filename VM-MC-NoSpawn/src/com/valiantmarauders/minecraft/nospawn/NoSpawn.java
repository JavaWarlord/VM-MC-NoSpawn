package com.valiantmarauders.minecraft.nospawn;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.location.CubedArea;

/**
 * 
 * @author JavaWarlord
 * @version 0.1
 * 
 *          v0.1 - Working NoSpawn area with constant values.
 * 
 *          v0.2 - Multiple NoSpawn areas
 * 
 *          v0.3 - Read areas from config.yml
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

	private List<CubedArea> areas;

	public void onEnable() {
		// Save a copy of the default config.yml if one is not there
		// this.saveDefaultConfig();
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new MobSpawnListener(this), this);
		areas = new ArrayList<CubedArea>();

		// Set<String> keys = getConfig().getKeys(false);
		// getLogger().info("Size: " + keys.size());
		// for (String s : keys)
		// getLogger().info(s);
		//
		// keys = getConfig().getKeys(true);
		// getLogger().info("Size: " + keys.size());
		// for (String s : keys)
		// getLogger().info(s);

		// Map<String, Object> values = this.getConfig()
		// .getConfigurationSection("worlds.world.areas").getValues(false);
		// getLogger().info("Size: " + values.size());

		/*
		 * Test code
		 */
		areas.add(new CubedArea(getServer().getWorld("newWorld"), 0, 60, 0,
				240, 80, 240));
		areas.add(new CubedArea(getServer().getWorld("newWorld"), 0, 60, 0,
				240, 0, 240));
		/*
		 * Test code
		 */
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("nospawn")
				|| cmd.getName().equalsIgnoreCase("nosp")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("reload")) {
					getServer().getPluginManager().disablePlugin(this);
					getServer().getPluginManager().enablePlugin(this);
				} else if (args[0].equalsIgnoreCase("test")) {
					getServer()
							.broadcastMessage("This is just a test command!");
				}
			}
			// If the player typed basic then do the following...
			// doSomething
			return true;
		} // If this has happened the function will return true.
			// If this hasn't happened the a value of false will be returned.
		return false;
	}

	public void onDisable() {
	}

	public void detectedSpawn(CreatureSpawnEvent event) {
		// TODO Auto-generated method stub
		// getLogger().info(
		// event.getEntity() + " at "
		// + event.getEntity().getLocation().getX() + ","
		// + event.getEntity().getLocation().getY() + ","
		// + event.getEntity().getLocation().getZ());
		if (isInNoSpawnArea(event.getEntity())) {
			event.setCancelled(true);
			// getLogger().info("In");
		} else {
			// getLogger().info("Out");
		}
	}

	private boolean isInNoSpawnArea(LivingEntity entity) {
		// TODO Auto-generated method stub
		for (CubedArea area : areas) {
			if (area.containsLocation(entity.getLocation())) {
				return true;
			}
		}
		return false;
	}
}
