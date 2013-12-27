package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.block.BlockClickHandler;
import com.valiantmarauders.minecraft.selection.CuboidSelectionManager;
import com.valiantmarauders.minecraft.selection.SelectionManager;

public class NoSpawnSelectionManager extends CuboidSelectionManager implements
		SelectionManager, BlockClickHandler {

	public NoSpawnSelectionManager(JavaPlugin plugin, Material selectionWand) {
		super(plugin, selectionWand);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void notify(Player player, ItemStack item, Block block) {
		// TODO Auto-generated method stub
		addPoint(player, item, block);
	}
}
