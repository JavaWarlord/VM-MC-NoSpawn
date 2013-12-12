/*
 * 
 */
package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * MobSpawnListener
 * 
 * @author JavaWarlord
 * @version 0.1
 * 
 */
public class MobSpawnListener implements Listener {

	private NoSpawn plugin;

	public MobSpawnListener(NoSpawn plugin) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		plugin.detectedSpawn(event);
	}
}
