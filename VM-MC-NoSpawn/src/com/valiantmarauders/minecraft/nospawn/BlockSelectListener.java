package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.block.BlockChangeDatabase;
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
		Block block = event.getClickedBlock();
		if (event.getItem() != null) {
			if (event.getItem().getType() == getSelectionToolType()) {
				if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
					if (selectionManager != null) {
						selectionManager.addPoint(event.getPlayer(),
								event.getItem(), block);
					}
				} else {
					BlockChangeDatabase blockDB = ((NoSpawn) plugin)
							.getBlockChangeDatabase();
					if (blockDB.isChanged(block.getLocation())) {
						blockDB.restore(block.getLocation());
					}
				}
			}
		}
	}

	public void setSelectionToolType(Material tool) {
		// TODO Auto-generated method stub
		this.tool = tool;
	}

	public Material getSelectionToolType() {
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
