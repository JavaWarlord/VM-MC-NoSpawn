package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.block.Cuboid;
import com.valiantmarauders.minecraft.command.CommandInterface;
import com.valiantmarauders.minecraft.location.CuboidManager;
import com.valiantmarauders.minecraft.selection.SelectionManager;

public class SetArea implements CommandInterface {
	private JavaPlugin plugin;
	private CuboidManager cuboidManager;
	private SelectionManager selectionManager;

	public SetArea(JavaPlugin plugin, CuboidManager cuboidManager,
			SelectionManager selectionManager) {
		super();
		this.plugin = plugin;
		this.cuboidManager = cuboidManager;
		this.selectionManager = selectionManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		// TODO Auto-generated method stub
		Cuboid cuboid = selectionManager.getCuboid(sender);
		if (cuboid == null) {
			plugin.getLogger().info(sender + " is trying to add a null area. ");
		} else {
			sender.sendMessage("Set area: " + cuboid);
			plugin.getLogger().info(sender + " is adding area " + cuboid);
			return cuboidManager.add(cuboid.clone());
		}
		return false;
	}
}
