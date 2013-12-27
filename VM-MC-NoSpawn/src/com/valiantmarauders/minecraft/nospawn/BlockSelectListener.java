package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.selection.SelectionManager;

/**
 * This class listens for a {@link Player} to click on a {@link Block} and
 * updates a {@link SelectionManager}
 * 
 * @author JavaWarlord
 * 
 */
public class BlockSelectListener implements Listener {
	private JavaPlugin plugin;
	private SelectionManager selectionManager;

	public BlockSelectListener(JavaPlugin plugin,
			SelectionManager selectionManager) {
		// TODO Auto-generated constructor stub
		this.setPlugin(plugin);
		this.selectionManager = selectionManager;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Material material = null;
		Block block = event.getClickedBlock();
		// Check to see if the player has something in his hands
		if (event.getItem() != null) {
			material = event.getItem().getType();
			if (selectionManager != null) {
				selectionManager.addPoint(player, material, block);
			}
		}
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}

	public void setPlugin(JavaPlugin plugin) {
		this.plugin = plugin;
	}
}
