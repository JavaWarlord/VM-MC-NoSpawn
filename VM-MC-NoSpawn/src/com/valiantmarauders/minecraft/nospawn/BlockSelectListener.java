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
	private Block block1 = null;
	private Block block2 = null;

	public BlockSelectListener(JavaPlugin plugin) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getItem() != null) {
			if (event.getItem().getType() == Material.ARROW) {
				if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
					block1 = event.getClickedBlock();
					event.getPlayer().sendMessage(
							"Point 1: " + block1.getX() + "," + block1.getY()
									+ "," + block1.getZ());
					plugin.getLogger().info(
							event.getPlayer() + " selected block "
									+ block1.getX() + "," + block1.getY() + ","
									+ block1.getZ());
				} else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					block2 = event.getClickedBlock();
					event.getPlayer().sendMessage(
							"Point 2: " + block2.getX() + "," + block2.getY()
									+ "," + block2.getZ());
					plugin.getLogger().info(
							event.getPlayer() + " selected block "
									+ block1.getX() + "," + block1.getY() + ","
									+ block1.getZ());
				}
			}
		}
	}

	@Override
	public Block getSelectedBlock() {
		// TODO Auto-generated method stub
		return block1;
	}

	@Override
	public Block getSelectedBlock(int index) {
		// TODO Auto-generated method stub
		if (index == 1)
			return block1;
		else
			return block2;
	}

	@Override
	public void setSelectedBlock(Block block) {
		// TODO Auto-generated method stub
		block1 = block;
	}

	@Override
	public void setSelectedBlock(int index, Block block) {
		// TODO Auto-generated method stub
		if (index == 1)
			block1 = block;
		else
			block2 = block;
	}
}
