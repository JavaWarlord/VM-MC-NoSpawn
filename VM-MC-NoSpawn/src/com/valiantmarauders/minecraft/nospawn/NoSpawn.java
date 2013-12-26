package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.Material;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.block.BlockChangeDatabase;
import com.valiantmarauders.minecraft.command.CommandHandler;
import com.valiantmarauders.minecraft.location.CuboidManager;
import com.valiantmarauders.minecraft.selection.CuboidSelectionManager;
import com.valiantmarauders.minecraft.selection.SelectionManager;

/**
 * 
 * @author JavaWarlord
 * @version 0.50
 * 
 *          v0.10 - Working NoSpawn area with constant values.
 * 
 *          v0.20 - Multiple NoSpawn areas
 * 
 *          v0.30 - Read areas from file
 * 
 *          v0.35 - Write areas to file
 * 
 *          v0.40 - Set boundaries of areas from inside game (command)
 * 
 *          v0.45 - Set boundaries of areas from inside game (tool)
 * 
 *          v0.50 - Delete areas
 * 
 *          v0.60 - View areas in game
 * 
 *          v0.70 - Resize areas
 * 
 *          v0.80 - Permissions
 * 
 */
public class NoSpawn extends JavaPlugin {

	private static final Material WAND = Material.ARROW;
	private CuboidManager cuboidManager;
	private SelectionManager selectionManager;
	private BlockChangeDatabase blockDB;
	private CommandHandler handler;

	public CuboidManager getAreaManager() {
		return cuboidManager;
	}

	public void setAreaManager(CuboidManager cuboidManager) {
		this.cuboidManager = cuboidManager;
	}

	public void onEnable() {
		// Save a copy of the default config.yml if one is not there
		// this.saveDefaultConfig();
		PluginManager pm = this.getServer().getPluginManager();
		cuboidManager = new NoSpawnAreaManager(this);
		selectionManager = new CuboidSelectionManager(this, WAND);
		blockDB = new NoSpawnBlockChangeDatabase(this);
		pm.registerEvents(new MobSpawnListener(this), this);
		pm.registerEvents(
				new BlockSelectListener(this, WAND, selectionManager), this);
		initializeCommands();
	}

	private void initializeCommands() {
		// TODO Auto-generated method stub
		handler = new CommandHandler();
		handler.register("reload", new Reload(this));
		handler.register("list", new ListAreas(this, cuboidManager));
		handler.register("set", new SetArea(this, cuboidManager,
				selectionManager));
		handler.register("remove", new RemoveArea(this, cuboidManager));
		handler.register("show", new ShowAreas(this, cuboidManager, blockDB));
		getCommand("nosp").setExecutor(handler);
		getCommand("nospawn").setExecutor(handler);
	}

	public void onDisable() {
		cuboidManager.save();
		blockDB.save();
	}

	public void detectedSpawn(CreatureSpawnEvent event) {
		// TODO Auto-generated method stub
		if (cuboidManager.contains(event.getEntity().getLocation())) {
			event.setCancelled(true);
		}
	}

	public BlockChangeDatabase getBlockChangeDatabase() {
		// TODO Auto-generated method stub
		return blockDB;
	}
}
