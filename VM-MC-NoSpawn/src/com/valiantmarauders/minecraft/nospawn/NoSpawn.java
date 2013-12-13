package com.valiantmarauders.minecraft.nospawn;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.location.CubedArea;

/**
 * 
 * @author JavaWarlord
 * @version 0.3
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
		this.saveDefaultConfig();
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new MobSpawnListener(this), this);
		areas = new ArrayList<CubedArea>();

		// Get list of worlds in worlds section.
		getLogger().info("----- KEY info -----");
		FileConfiguration config = getConfig();

		// Get a Set of all the worlds
		Set<String> worlds = getConfig().getConfigurationSection("worlds")
				.getKeys(false);
		getLogger().info("Read: " + worlds.size() + " worlds.");
		// For each world, get the defined areas
		for (String world : worlds) {
			getLogger().info(world);
			String areasInWorld = config
					.getString("worlds." + world + ".areas");
			getLogger().info(areasInWorld);
			if (areasInWorld != null) {
				// Strip the brackets
				areasInWorld = areasInWorld.substring(1,
						areasInWorld.length() - 1);
				// getLogger().info(areasInWorld);
				// areasInWorld = areasInWorld.replace(" ", " ");
				// getLogger().info(areasInWorld);
				String[] cubes = areasInWorld.split(",");
				for (int i = 0; i < cubes.length; i++) {
					cubes[i] = cubes[i].trim();
					// getLogger().info(cubes[i]);
					String[] points = cubes[i].split(" ");
					// for (int j = 0; j < points.length; j++) {
					// getLogger().info("[" + points[j] + "]");
					areas.add(new CubedArea(getServer().getWorld(world),
							Integer.valueOf(points[0].trim()), Integer
									.valueOf(points[1].trim()), Integer
									.valueOf(points[2].trim()), Integer
									.valueOf(points[3].trim()), Integer
									.valueOf(points[4].trim()), Integer
									.valueOf(points[5].trim())));
					// }
				}
			}
		}
		getLogger().info("--------------------");
		for (CubedArea a : areas) {
			getLogger().info(a.toString());
		}
		/*
		 * Test code
		 */
		// areas.add(new CubedArea(getServer().getWorld("newWorld"), 0, 60, 0,
		// 240, 115, 240));
		// areas.add(new CubedArea(getServer().getWorld("newWorld"), 0, 60, 0,
		// 240, 0, 240));
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
					getServer().broadcastMessage(
							"This is just a test command!!!");
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
