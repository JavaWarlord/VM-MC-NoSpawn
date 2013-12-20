package com.valiantmarauders.minecraft.nospawn;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.location.Cuboid;

public class SelectionManager {
	// Location[] locations;
	private HashMap<Player, Cuboid> cuboids;
	private JavaPlugin plugin;

	public SelectionManager(JavaPlugin plugin) {
		this.plugin = plugin;
		plugin.getLogger().info("Creating Selection Manager.");
		cuboids = new HashMap<Player, Cuboid>();
	}

	public Cuboid getCuboid(CommandSender sender) {
		// TODO Auto-generated method stub
		return cuboids.get(sender);
	}

	public void update(Player player, Location location, int index) {
		// TODO Auto-generated method stub
		Cuboid cuboid = cuboids.get(player);
		if (cuboid == null) {
			// This player hasn't selected anything yet.
			plugin.getLogger().info(
					"Creating new SelectionManager for " + player);
			cuboid = new Cuboid(location.getWorld(), location, location);
			cuboids.put(player, cuboid);
		}
		if (index == 1)
			cuboid.setLocation1(location);
		else
			cuboid.setLocation2(location);
		player.sendMessage("Point " + index + ": " + location.getX() + ","
				+ location.getY() + "," + location.getZ());
		plugin.getLogger().info(
				player + " selected block " + index + " " + location.getX()
						+ "," + location.getY() + "," + location.getZ());
	}
}
