package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.selection.SelectionManager;

public class BlockSelectListener implements Listener {
	private JavaPlugin plugin;
	private Material tool;
	private SelectionManager selectionManager;

	public BlockSelectListener(JavaPlugin plugin, Material tool,
			SelectionManager selectionManager) {
		// TODO Auto-generated constructor stub
		this.setPlugin(plugin);
		this.tool = tool;
		this.selectionManager = selectionManager;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getItem() != null) {
			if (event.getItem().getType() == getSelectionTool()) {
				Block block = event.getClickedBlock();
				int index = 0;
				if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
					index = 1;
				} else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					index = 2;
				}
				if (selectionManager != null) {
					selectionManager.addPoint(event.getPlayer(), event
							.getItem().getType(), block);
				}
			}
		}
	}

	public void setSelectionTool(Material tool) {
		// TODO Auto-generated method stub
		this.tool = tool;
	}

	public Material getSelectionTool() {
		// TODO Auto-generated method stub
		return tool;
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}

	public void setPlugin(JavaPlugin plugin) {
		this.plugin = plugin;
	}
}
