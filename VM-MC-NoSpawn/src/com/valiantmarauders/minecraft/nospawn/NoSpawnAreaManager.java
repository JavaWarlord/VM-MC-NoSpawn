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

public class NoSpawnAreaManager {

	private JavaPlugin plugin;
	private String fileName;
	private List<NoSpawnArea> areas;

	public NoSpawnAreaManager(JavaPlugin plugin) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
		fileName = "areas.dat";
		areas = load();
		if (areas == null) {
			areas = new ArrayList<NoSpawnArea>();
		}
	}

	public List<NoSpawnArea> load() {
		plugin.getLogger().info("Loading areas");
		File file = new File("plugins/NoSpawn/" + fileName);
		List<NoSpawnArea> areas = new ArrayList<NoSpawnArea>();
		if (file.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream(file.getAbsolutePath()));
				while (ois.available() > 0) {
					areas.add((NoSpawnArea) ois.readObject());
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
			for (NoSpawnArea area : areas) {
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
			for (NoSpawnArea a : areas) {
				sender.sendMessage(a.toString());
			}
		}
	}

	public boolean add(NoSpawnArea area) {
		// TODO Auto-generated method stub
		if (areas.contains(area))
			return false;
		else
			return areas.add(area);
	}

	public boolean contains(Location location) {
		// TODO Auto-generated method stub
		for (NoSpawnArea area : areas) {
			if (area.contains(location)) {
				return true;
			}
		}
		return false;
	}

	public NoSpawnArea getArea(Location location) {
		// TODO Auto-generated method stub
		for (NoSpawnArea area : areas) {
			if (area.contains(location)) {
				return area;
			}
		}
		return null;
	}

	public boolean remove(NoSpawnArea area) {
		// TODO Auto-generated method stub
		return areas.remove(area);
	}

	public void removeAll() {
		// TODO Auto-generated method stub
		areas.clear();
	}
}
