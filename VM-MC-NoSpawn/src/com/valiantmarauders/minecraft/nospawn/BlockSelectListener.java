package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.common.BlockSelector;

public class BlockSelectListener implements BlockSelector, Listener {
	private JavaPlugin plugin;
	private Material tool;
	// private Block block1 = null;
	// private Block block2 = null;
	private SelectionManager selectionManager;

	public BlockSelectListener(JavaPlugin plugin, Material tool,
			SelectionManager selectionManager) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
		this.tool = tool;
		this.selectionManager = selectionManager;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getItem() != null) {
			if (event.getItem().getType() == tool) {
				Block block = event.getClickedBlock();
				int index = 0;
				if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
					index = 1;
				} else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					index = 2;
				}
				selectionManager.add(event.getPlayer(), block.getLocation(),
						index);

				event.getPlayer().sendMessage(
						"Point " + index + ": " + block.getX() + ","
								+ block.getY() + "," + block.getZ());
				plugin.getLogger().info(
						event.getPlayer() + " selected block " + block.getX()
								+ "," + block.getY() + "," + block.getZ());
			}
		}
	}

	@Override
	public void setSelectionTool(Material tool) {
		// TODO Auto-generated method stub
		this.tool = tool;
	}

	@Override
	public Material getSelectionTool() {
		// TODO Auto-generated method stub
		return tool;
	}
}
