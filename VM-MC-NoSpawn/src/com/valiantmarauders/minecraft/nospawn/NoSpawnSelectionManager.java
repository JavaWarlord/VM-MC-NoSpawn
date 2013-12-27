package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.selection.CuboidSelectionManager;
import com.valiantmarauders.minecraft.selection.SelectionManager;

public class NoSpawnSelectionManager extends CuboidSelectionManager implements
		SelectionManager {

	public NoSpawnSelectionManager(JavaPlugin plugin, Material selectionWand) {
		super(plugin, selectionWand);
		// TODO Auto-generated constructor stub
	}

}
