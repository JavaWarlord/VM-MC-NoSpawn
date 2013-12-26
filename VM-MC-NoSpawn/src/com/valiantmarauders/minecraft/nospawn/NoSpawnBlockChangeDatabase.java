package com.valiantmarauders.minecraft.nospawn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.block.BlockChangeDatabase;

public class NoSpawnBlockChangeDatabase implements BlockChangeDatabase {

	private JavaPlugin plugin;
	private Map<Block, Material> blocks;
	private String fileName;

	public NoSpawnBlockChangeDatabase(JavaPlugin plugin) {
		// TODO Auto-generated constructor stub
		this.setPlugin(plugin);
		this.blocks = new HashMap<Block, Material>();
		fileName = "blocks.dat";
		Map<Block, Material> testblocks = load();
	}

	@Override
	public void change(Block block, Material material) {
		// TODO Auto-generated method stub
		blocks.put(block, block.getType());
		block.setType(material);
	}

	@Override
	public void restore(Block block) {
		// TODO Auto-generated method stub
		block.setType(blocks.remove(block));
	}

	@Override
	public boolean isChanged(Block block) {
		// TODO Auto-generated method stub
		return blocks.containsKey(block);
	}

	@Override
	public void restoreAll() {
		// TODO Auto-generated method stub
		for (Entry<Block, Material> entry : blocks.entrySet()) {
			entry.getKey().setType(entry.getValue());
		}
		blocks.clear();
	}

	@Override
	public Map<Block, Material> load() {
		// TODO Auto-generated method stub
		plugin.getLogger().info("Loading blocks");
		File file = new File("plugins/NoSpawn/" + fileName);
		// List<Cuboid> areas = new ArrayList<Cuboid>();
		if (file.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream(file.getAbsolutePath()));
				Object object = ois.readObject();
				@SuppressWarnings("unchecked")
				ArrayList<String> result = (ArrayList<String>) object;
				for (String line : result) {
					plugin.getLogger().info(line);
				}
				ois.close();
			} catch (Exception e) {
				plugin.getLogger().warning("Error reading " + fileName);
				plugin.getLogger().warning(e.toString());
			}
		}
		return null;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		File file = new File("plugins/NoSpawn/" + fileName);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(file.getAbsolutePath()));
			ArrayList<String> format = new ArrayList<String>();
			for (Entry<Block, Material> entry : blocks.entrySet()) {
				format.add(entry.getKey().toString());
				format.add(entry.getValue().toString());
			}
			oos.writeObject(format);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			plugin.getLogger().warning("Error writing " + fileName);
			plugin.getLogger().warning(e.toString());
		}
	}
}
