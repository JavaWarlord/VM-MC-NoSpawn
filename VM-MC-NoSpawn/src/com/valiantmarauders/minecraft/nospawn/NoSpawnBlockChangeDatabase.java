package com.valiantmarauders.minecraft.nospawn;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.block.BlockChangeDatabase;

public interface BlockChangeDatabasee implements BlockChangeDatabase {

	private JavaPlugin plugin;
	private Map<Block, Material> blocks;

	public NoSpawnBlockChangeDatabase(JavaPlugin plugin) {
		// TODO Auto-generated constructor stub
		this.setPlugin(plugin);
		this.blocks = new HashMap<Block, Material>();
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

	public JavaPlugin getPlugin() {
		return plugin;
	}

	public void setPlugin(JavaPlugin plugin) {
		this.plugin = plugin;
	}
}
