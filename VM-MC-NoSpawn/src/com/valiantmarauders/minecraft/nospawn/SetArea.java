package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.command.CommandInterface;
import com.valiantmarauders.minecraft.location.Cuboid;

public class SetArea implements CommandInterface {
	private JavaPlugin plugin;
	private AreaManager areaManager;
	private SelectionManager selectionManager;

	public SetArea(JavaPlugin plugin, AreaManager areaManager,
			SelectionManager selectionManager) {
		super();
		this.plugin = plugin;
		this.areaManager = areaManager;
		this.selectionManager = selectionManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		// TODO Auto-generated method stub
		Cuboid cuboid = selectionManager.getCuboid(sender);
		plugin.getLogger().info(sender + " is adding an area. " + cuboid);
		return areaManager.add(cuboid);
	}
}
