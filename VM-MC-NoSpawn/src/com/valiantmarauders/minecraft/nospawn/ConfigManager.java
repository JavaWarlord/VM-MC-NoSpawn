package com.valiantmarauders.minecraft.nospawn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.location.CubedArea;

public class ConfigManager {

	private JavaPlugin plugin;
	private String fileName;

	public ConfigManager(JavaPlugin plugin) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
		fileName = "Areas.dat";
	}

	public void loadAreas() {
		File file = new File("plugins/NoSpawn/" + fileName);
		if (file.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream(file.getAbsolutePath()));
				List<CubedArea> areas = (List<CubedArea>) ois.readObject();

				ois.close();
			} catch (Exception e) {
				plugin.getLogger().warning("Couldn't read " + fileName);
			}
		}
	}

	public void saveAreas() {
		// TODO Auto-generated method stub
		File file = new File("plugins/NoSpawn/" + fileName);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(file.getAbsolutePath()));
			oos.writeObject(((NoSpawn) plugin).getAreas());
			oos.flush();
			oos.close();
		} catch (Exception e) {
			plugin.getLogger().warning("Couldn't save " + fileName);
		}
	}
}
