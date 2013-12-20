package com.valiantmarauders.minecraft.nospawn;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import com.valiantmarauders.minecraft.location.Cuboid;

public class SelectionManager {
	// Location[] locations;
	private HashMap<CommandSender, Cuboid> cuboids;

	public SelectionManager() {
		// this.locations = new Location[2];
		cuboids = new HashMap<CommandSender, Cuboid>();
	}

	public void add(CommandSender sender, Location location, int index) {
		// TODO Auto-generated method stub
		Cuboid cuboid = cuboids.get(sender);
		if (cuboid == null) {
			cuboid = new Cuboid(location.getWorld(), location);
		} else {
			if (index == 1)
				cuboid.setLocation1(location);
			else
				cuboid.setLocation2(location);
		}
	}

	public Cuboid getCuboid(CommandSender sender) {
		// TODO Auto-generated method stub
		return cuboids.get(sender);
	}
}
