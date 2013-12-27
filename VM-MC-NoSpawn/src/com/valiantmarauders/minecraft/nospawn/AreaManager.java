package com.valiantmarauders.minecraft.nospawn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.block.Cuboid;

public class AreaManager {

	private JavaPlugin plugin;
	private String fileName;
	private List<Cuboid> areas;

	public AreaManager(JavaPlugin plugin) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
		fileName = "areas.dat";
		areas = load();
		if (areas == null) {
			areas = new ArrayList<Cuboid>();
		}
	}

	public List<Cuboid> load() {
		plugin.getLogger().info("Loading areas");
		File file = new File("plugins/NoSpawn/" + fileName);
		List<Cuboid> areas = new ArrayList<Cuboid>();
		if (file.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream(file.getAbsolutePath()));
				while (ois.available() > 0) {
					areas.add((Cuboid) ois.readObject());
				}
				ois.close();
			} catch (Exception e) {
				plugin.getLogger().warning("Error reading " + fileName);
				plugin.getLogger().warning(e.toString());
			}
		}
		return areas;
	}

	public void save() {
		// TODO Auto-generated method stub
		File file = new File("plugins/NoSpawn/" + fileName);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(file.getAbsolutePath()));
			for (Cuboid area : areas) {
				oos.writeObject(area);
			}
			oos.flush();
			oos.close();
		} catch (Exception e) {
			plugin.getLogger().warning("Error writing " + fileName);
			plugin.getLogger().warning(e.toString());
		}
	}

	public void display(CommandSender sender) {
		// TODO Auto-generated method stub
		if (areas.isEmpty()) {
			sender.sendMessage("There are no defined areas.");
		} else {
			sender.sendMessage("Areas");
			for (Cuboid a : areas) {
				sender.sendMessage(a.toString());
			}
		}
	}

	public boolean add(Cuboid cuboid) {
		// TODO Auto-generated method stub
		if (areas.contains(cuboid))
			return false;
		else
			return areas.add(cuboid);
	}

	public boolean contains(Location location) {
		// TODO Auto-generated method stub
		for (Cuboid area : areas) {
			if (area.contains(location)) {
				return true;
			}
		}
		return false;
	}

	public Cuboid getArea(Location location) {
		// TODO Auto-generated method stub
		for (Cuboid area : areas) {
			if (area.contains(location)) {
				return area;
			}
		}
		return null;
	}

	public boolean remove(Cuboid area) {
		// TODO Auto-generated method stub
		return areas.remove(area);
	}

	public void removeAll() {
		// TODO Auto-generated method stub
		areas.clear();
	}
}
