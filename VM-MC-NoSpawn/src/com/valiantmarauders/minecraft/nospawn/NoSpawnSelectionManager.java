package com.valiantmarauders.minecraft.nospawn;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.block.BlockChangeDatabase;
import com.valiantmarauders.minecraft.block.BlockClickHandler;
import com.valiantmarauders.minecraft.block.FlatFileBlockChangeDatabase;
import com.valiantmarauders.minecraft.selection.CuboidSelection;
import com.valiantmarauders.minecraft.selection.CuboidSelectionManager;
import com.valiantmarauders.minecraft.selection.DisplayableSelection;
import com.valiantmarauders.minecraft.selection.Selection;

public class NoSpawnSelectionManager extends CuboidSelectionManager implements
		BlockClickHandler, DisplayableSelection {

	private BlockChangeDatabase blockChangeDB;
	private Material guideMaterial = Material.WOOL;

	public NoSpawnSelectionManager(JavaPlugin plugin, Material wand) {
		// TODO Auto-generated constructor stub
		super(plugin, wand);
		blockChangeDB = new FlatFileBlockChangeDatabase(plugin);
	}

	@Override
	public void notify(Player player, Action action, Material material,
			Block block) {
		// TODO Auto-generated method stub
		CuboidSelection selection = selections.get(player);
		if (material == getWand()) {
			int index = 0;
			if (action == Action.LEFT_CLICK_BLOCK) {
				index = 0;
			}
			if (action == Action.RIGHT_CLICK_BLOCK) {
				index = 1;
			}
			removeGuides(selection);
			selection.set(index, block.getLocation());
			addGuides(selection);
		} else {
			blockChangeDB.restore(block.getLocation());
		}

	}

	@Override
	public void removeGuides(Selection selection) {
		// TODO Auto-generated method stub
		List<Location> guideBlockLocations = new ArrayList<Location>();
		guideBlockLocations.addAll(selection.getPoints());
		List<Block> guideBlocks = calculateGuides(selection);
		for (Block b : guideBlocks) {
			guideBlockLocations.add(b.getLocation());
		}
		for (Location loc : guideBlockLocations) {
			blockChangeDB.restore(loc);
		}
	}

	@Override
	public void addGuides(Selection selection) {
		// TODO Auto-generated method stub
		List<Location> guideBlockLocations = new ArrayList<Location>();
		guideBlockLocations.addAll(selection.getPoints());
		List<Block> guideBlocks = calculateGuides(selection);
		for (Block b : guideBlocks) {
			guideBlockLocations.add(b.getLocation());
		}
		for (Location loc : guideBlockLocations) {
			blockChangeDB.add(loc, guideMaterial);
		}
	}

	@Override
	public List<Block> calculateGuides(Selection selection) {
		// TODO Auto-generated method stub
		World world = selection.getPoints().get(0).getWorld();
		List<Block> guides = new ArrayList<Block>();
		int minX = (int) Math.min(selection.getPoints().get(0).getBlockX(),
				selection.getPoints().get(1).getBlockX());
		int maxX = (int) Math.max(selection.getPoints().get(0).getBlockX(),
				selection.getPoints().get(1).getBlockX());
		int minY = (int) Math.min(selection.getPoints().get(0).getBlockY(),
				selection.getPoints().get(1).getBlockY());
		int maxY = (int) Math.max(selection.getPoints().get(0).getBlockY(),
				selection.getPoints().get(1).getBlockY());
		int minZ = (int) Math.min(selection.getPoints().get(0).getBlockZ(),
				selection.getPoints().get(1).getBlockZ());
		int maxZ = (int) Math.max(selection.getPoints().get(0).getBlockZ(),
				selection.getPoints().get(1).getBlockZ());
		for (int x = minX; x < maxX; x++) {
			if (x != minX && x != maxX)
				continue;
			for (int y = minY; y < maxY; y++) {
				if (y != minY && y != maxY)
					continue;
				for (int z = minZ; z < maxZ; z++) {
					if (z != minZ && z != maxZ)
						continue;
					guides.add(new Location(world, x, y, z).getBlock());
				}
			}
		}
		return guides;
	}
}
