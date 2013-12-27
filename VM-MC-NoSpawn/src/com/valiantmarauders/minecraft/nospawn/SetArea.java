package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.block.Cuboid;
import com.valiantmarauders.minecraft.command.CommandInterface;
import com.valiantmarauders.minecraft.selection.SelectionManager;

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
		if (sender instanceof Player) {
			Cuboid cuboid = new Cuboid(selectionManager.getSelection(
					(Player) sender).getPoints());
			sender.sendMessage("Set area: " + cuboid);
			plugin.getLogger().info(sender + " is adding area " + cuboid);
			return areaManager.add(cuboid.clone());
		}
		return false;
	}
}
