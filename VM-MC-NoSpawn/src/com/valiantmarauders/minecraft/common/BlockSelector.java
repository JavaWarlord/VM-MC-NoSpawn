package com.valiantmarauders.minecraft.common;

import org.bukkit.block.Block;

public interface BlockSelector {
	public Block getSelectedBlock();

	public Block getSelectedBlock(int index);

	public void setSelectedBlock(Block block);

	public void setSelectedBlock(int index, Block block);
}
