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
	private Material guideMaterial = Material.GLASS;

	public NoSpawnSelectionManager(JavaPlugin plugin, Material wand) {
		// TODO Auto-generated constructor stub
		super(plugin, wand);
		blockChangeDB = new FlatFileBlockChangeDatabase(plugin);
	}

	@Override
	public void notify(Player player, Action action, Material material,
			Block block) {
		// TODO Auto-generated method stub
		if (material == getWand()) {
			int index = 0;
			if (action == Action.LEFT_CLICK_BLOCK) {
				index = 0;
			}
			if (action == Action.RIGHT_CLICK_BLOCK) {
				index = 1;
			}
			CuboidSelection selection = selections.get(player);
			if (selection == null) {
				selection = new CuboidSelection();
				selections.put(player, selection);
			}
			if (selection.getNumberOfPoints() >= 2) {
				removeGuides(selection);
			}
			selection.set(index, block.getLocation());
			if (selection.getNumberOfPoints() >= 2) {
				addGuides(selection);
			}
		} else {
			blockChangeDB.restore(block.getLocation());
		}

	}

	@Override
	public void removeGuides(Selection selection) {
		// TODO Auto-generated method stub
		if (selection.getPoints() != null) {
			List<Location> guideBlockLocations = new ArrayList<Location>();
			guideBlockLocations.addAll(selection.getPoints());
			List<Block> guideBlocks = calculateGuides(selection);
			if (guideBlocks != null) {
				for (Block b : guideBlocks) {
					guideBlockLocations.add(b.getLocation());
				}
				for (Location loc : guideBlockLocations) {
					blockChangeDB.restore(loc);
				}
			}
		}
	}

	@Override
	public void addGuides(Selection selection) {
		// TODO Auto-generated method stub
		List<Location> guideBlockLocations = new ArrayList<Location>();
		guideBlockLocations.addAll(selection.getPoints());
		List<Block> guideBlocks = calculateGuides(selection);
		if (guideBlocks != null) {
			for (Block b : guideBlocks) {
				guideBlockLocations.add(b.getLocation());
			}
			for (Location loc : guideBlockLocations) {
				blockChangeDB.add(loc, guideMaterial);
			}
		}
	}

	@Override
	public List<Block> calculateGuides(Selection selection) {
		// TODO Auto-generated method stub
		List<Block> guides = null;
		List<Location> selectionPoints = selection.getPoints();
		if (selectionPoints != null) {
			if (selectionPoints.get(0) != null
					&& selectionPoints.get(1) != null) {
				World world = selectionPoints.get(0).getWorld();
				guides = new ArrayList<Block>();
				int minX = (int) Math.min(selectionPoints.get(0).getBlockX(),
						selectionPoints.get(1).getBlockX());
				int maxX = (int) Math.max(selectionPoints.get(0).getBlockX(),
						selectionPoints.get(1).getBlockX());
				int minY = (int) Math.min(selectionPoints.get(0).getBlockY(),
						selectionPoints.get(1).getBlockY());
				int maxY = (int) Math.max(selectionPoints.get(0).getBlockY(),
						selectionPoints.get(1).getBlockY());
				int minZ = (int) Math.min(selectionPoints.get(0).getBlockZ(),
						selectionPoints.get(1).getBlockZ());
				int maxZ = (int) Math.max(selectionPoints.get(0).getBlockZ(),
						selectionPoints.get(1).getBlockZ());

				guides.add(new Location(world, minX, minY, minZ).getBlock());
				guides.add(new Location(world, maxX, minY, minZ).getBlock());
				guides.add(new Location(world, minX, maxY, minZ).getBlock());
				guides.add(new Location(world, minX, minY, maxZ).getBlock());
				guides.add(new Location(world, minX, maxY, maxZ).getBlock());
				guides.add(new Location(world, maxX, minY, maxZ).getBlock());
				guides.add(new Location(world, maxX, maxY, minZ).getBlock());
				guides.add(new Location(world, maxX, maxY, maxZ).getBlock());

				// for (int x = minX; x <= maxX; x++) {
				// for (int y = minY; y <= maxY; y++) {
				// for (int z = minZ; z <= maxZ; z++) {
				// if (x == minX || x == maxX || y == minY
				// || y == maxY || z == minZ || z == maxZ)
				// guides.add(new Location(world, x, y, z)
				// .getBlock());
				// }
				// }
				// }
			}
		}
		return guides;
	}

	@Override
	public void restoreAll() {
		// TODO Auto-generated method stub
		blockChangeDB.restoreAll();
	}
}
