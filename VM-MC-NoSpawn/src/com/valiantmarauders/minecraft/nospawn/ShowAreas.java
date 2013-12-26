package com.valiantmarauders.minecraft.nospawn;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.block.BlockChangeDatabase;
import com.valiantmarauders.minecraft.block.Cuboid;
import com.valiantmarauders.minecraft.command.CommandInterface;
import com.valiantmarauders.minecraft.location.CuboidManager;

public class ShowAreas implements CommandInterface {
	private JavaPlugin plugin;
	private CuboidManager cuboidManager;
	private BlockChangeDatabase blockDB;
	private final Material corner = Material.GOLD_BLOCK;

	public ShowAreas(JavaPlugin plugin, CuboidManager cuboidManager,
			BlockChangeDatabase blockDB) {
		// TODO Auto-generated constructor stub
		this.setPlugin(plugin);
		this.cuboidManager = cuboidManager;
		this.blockDB = blockDB;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		// TODO Auto-generated method stub
		// go through area manager
		List<Cuboid> areas = cuboidManager.getCuboids();
		// for each location, in each cuboid, mark the corner with gold
		for (Cuboid c : areas) {
			Block block = c.getLocation1().getBlock();
			blockDB.change(block, corner);
			block = c.getLocation2().getBlock();
			blockDB.change(block, corner);
		}
		return true;
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}

	public void setPlugin(JavaPlugin plugin) {
		this.plugin = plugin;
	}
}
