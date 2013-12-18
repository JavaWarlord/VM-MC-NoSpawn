package com.valiantmarauders.minecraft.common;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;

public interface BlockSelector {
	public Block getSelectedBlock();

	public Block getSelectedBlock(int index);

	public void setSelectedBlock(Block block);

	public void setSelectedBlock(int index, Block block);

	public void setSelectionTool(Material tool);

	public Material getSelectionTool();

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
	public void onPlayerInteract(PlayerInteractEvent event);
}
