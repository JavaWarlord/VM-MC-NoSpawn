package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.Material;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.block.BlockClickHandler;
import com.valiantmarauders.minecraft.block.PlayerClickBlockListener;
import com.valiantmarauders.minecraft.command.CommandHandler;
import com.valiantmarauders.minecraft.selection.CuboidSelectionManager;

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

	private NoSpawnAreaManager noSpawnAreaManager;
	private CuboidSelectionManager noSpawnSelectionManager;

	public NoSpawnAreaManager getAreaManager() {
		return noSpawnAreaManager;
	}

	public void setAreaManager(NoSpawnAreaManager noSpawnAreaManager) {
		this.noSpawnAreaManager = noSpawnAreaManager;
	}

	public void onEnable() {
		// Save a copy of the default config.yml if one is not there
		this.saveDefaultConfig();
		PluginManager pm = this.getServer().getPluginManager();
		noSpawnAreaManager = new NoSpawnAreaManager(this);
		noSpawnSelectionManager = new NoSpawnSelectionManager(this,
				Material.ARROW);
		pm.registerEvents(new MobSpawnListener(this), this);
		pm.registerEvents(new PlayerClickBlockListener(
				(BlockClickHandler) noSpawnSelectionManager), this);
		initializeCommands();
	}

	private void initializeCommands() {
		// TODO Auto-generated method stub
		CommandHandler handler = new CommandHandler();
		handler.register("reload", new Reload(this));
		handler.register("list", new ListNoSpawnAreas(this, noSpawnAreaManager));
		handler.register("set", new SetNoSpawnArea(this, noSpawnAreaManager,
				noSpawnSelectionManager));
		handler.register("remove", new RemoveNoSpawnArea(this,
				noSpawnAreaManager));
		getCommand("nosp").setExecutor(handler);
		getCommand("nospawn").setExecutor(handler);
	}

	public void onDisable() {
		noSpawnAreaManager.save();
	}

	public void detectedSpawn(CreatureSpawnEvent event) {
		// TODO Auto-generated method stub
		if (noSpawnAreaManager.contains(event.getEntity().getLocation())) {
			event.setCancelled(true);
		}
	}
}
