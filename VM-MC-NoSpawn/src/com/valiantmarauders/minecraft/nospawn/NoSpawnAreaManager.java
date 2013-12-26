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
import com.valiantmarauders.minecraft.location.CuboidManager;

public class NoSpawnAreaManager implements CuboidManager {

	private JavaPlugin plugin;
	private String fileName;
	private List<Cuboid> _cuboids;

	public NoSpawnAreaManager(JavaPlugin plugin) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
		fileName = "areas.dat";
		_cuboids = load();
		if (_cuboids == null) {
			_cuboids = new ArrayList<Cuboid>();
		}
	}

	@Override
	public List<Cuboid> load() {
		plugin.getLogger().info("Loading areas");
		File file = new File("plugins/NoSpawn/" + fileName);
		List<Cuboid> areas = new ArrayList<Cuboid>();
		if (file.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream(file.getAbsolutePath()));
				Object object = ois.readObject();
				@SuppressWarnings("unchecked")
				ArrayList<String> result = (ArrayList<String>) object;
				for (String areaText : result) {
					String[] values = areaText.split(",");
					areas.add(new Cuboid(
							plugin.getServer().getWorld(values[0]), Integer
									.valueOf(values[1]), Integer
									.valueOf(values[2]), Integer
									.valueOf(values[3]), Integer
									.valueOf(values[4]), Integer
									.valueOf(values[5]), Integer
									.valueOf(values[6])));
				}
				ois.close();
			} catch (Exception e) {
				plugin.getLogger().warning("Error reading " + fileName);
				plugin.getLogger().warning(e.toString());
			}
		}
		return areas;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		File file = new File("plugins/NoSpawn/" + fileName);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(file.getAbsolutePath()));
			ArrayList<String> format = new ArrayList<String>();
			for (Cuboid area : _cuboids) {
				format.add(area.toSaveFormat());
			}
			oos.writeObject(format);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			plugin.getLogger().warning("Error writing " + fileName);
			plugin.getLogger().warning(e.toString());
		}
	}

	@Override
	public void display(CommandSender sender) {
		// TODO Auto-generated method stub
		if (_cuboids.isEmpty()) {
			sender.sendMessage("There are no defined areas.");
		} else {
			sender.sendMessage("Areas");
			for (Cuboid a : _cuboids) {
				sender.sendMessage(a.toString());
			}
		}
	}

	@Override
	public boolean add(Cuboid cuboid) {
		// TODO Auto-generated method stub
		if (_cuboids.contains(cuboid))
			return false;
		else
			return _cuboids.add(cuboid);
	}

	@Override
	public boolean contains(Location location) {
		// TODO Auto-generated method stub
		for (Cuboid area : _cuboids) {
			if (area.contains(location)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(Cuboid area) {
		// TODO Auto-generated method stub
		return _cuboids.remove(area);
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		_cuboids.clear();
	}

	@Override
	public List<Cuboid> getCuboids() {
		// TODO Auto-generated method stub
		return _cuboids;
	}

	@Override
	public Cuboid get(Location location) {
		// TODO Auto-generated method stub
		for (Cuboid cuboid : _cuboids) {
			if (cuboid.contains(location)) {
				return cuboid;
			}
		}
		return null;
	}
}
