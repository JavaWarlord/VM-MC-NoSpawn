package com.valiantmarauders.minecraft.nospawn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.location.CubedArea;

public class FileManager {

	private JavaPlugin plugin;

	public FileManager(JavaPlugin plugin) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
	}

	public void saveAreas(List<CubedArea> areas) {
		// TODO Auto-generated method stub
		String fileName = "Areas.dat";
		File file = new File("plugins/NoSpawn/" + fileName);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(file.getAbsolutePath()));
			oos.writeObject(areas);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			plugin.getLogger().warning("Couldn't save " + fileName);
		}
	}
}
