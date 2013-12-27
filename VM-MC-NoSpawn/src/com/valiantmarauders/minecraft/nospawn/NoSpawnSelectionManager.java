package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.block.BlockClickHandler;
import com.valiantmarauders.minecraft.selection.CuboidSelectionManager;
import com.valiantmarauders.minecraft.selection.SelectionManager;

public class NoSpawnSelectionManager extends CuboidSelectionManager implements
		SelectionManager, BlockClickHandler {

	public NoSpawnSelectionManager(JavaPlugin plugin, Material material) {
		super(plugin, material);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void notify(Player player, Material material, Block block) {
		// TODO Auto-generated method stub
		addPoint(player, material, block);
	}
}
