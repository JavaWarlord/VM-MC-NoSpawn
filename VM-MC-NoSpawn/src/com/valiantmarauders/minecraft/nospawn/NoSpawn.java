package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.Material;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.command.CommandHandler;

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

	private AreaManager areaManager;
	private SelectionManager selectionManager;

	public AreaManager getAreaManager() {
		return areaManager;
	}

	public void setAreaManager(AreaManager areaManager) {
		this.areaManager = areaManager;
	}

	// private MobSpawnListener msl;
	// private BlockSelectListener bsl;
	// private HashMap<String, CommandExecutor> commands;

	public void onEnable() {
		// Save a copy of the default config.yml if one is not there
		// this.saveDefaultConfig();
		// commands = new HashMap<String, CommandExecutor>();
		PluginManager pm = this.getServer().getPluginManager();
		// msl = new MobSpawnListener(this);
		// bsl = new BlockSelectListener(this, Material.ARROW,
		// selectionManager);
		areaManager = new AreaManager(this);
		selectionManager = new SelectionManager(this);
		pm.registerEvents(new MobSpawnListener(this), this);
		pm.registerEvents(new BlockSelectListener(this, Material.ARROW,
				selectionManager), this);
		initializeCommands();
	}

	private void initializeCommands() {
		// TODO Auto-generated method stub
		CommandHandler handler = new CommandHandler();
		handler.register("reload", new Reload(this));
		handler.register("list", new ListAreas(this, areaManager));
		handler.register("set",
				new SetArea(this, areaManager, selectionManager));
		handler.register("remove", new RemoveArea(this, areaManager));
		getCommand("nosp").setExecutor(handler);
		getCommand("nospawn").setExecutor(handler);
	}

	public void onDisable() {
		areaManager.save();
	}

	public void detectedSpawn(CreatureSpawnEvent event) {
		// TODO Auto-generated method stub
		if (areaManager.contains(event.getEntity().getLocation())) {
			event.setCancelled(true);
		}
	}
}
