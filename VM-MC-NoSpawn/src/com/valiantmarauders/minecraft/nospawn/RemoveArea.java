package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.block.Cuboid;
import com.valiantmarauders.minecraft.command.CommandInterface;

public class RemoveArea implements CommandInterface {
	private JavaPlugin plugin;
	private AreaManager areaManager;

	public RemoveArea(JavaPlugin plugin, AreaManager areaManager) {
		super();
		this.plugin = plugin;
		this.areaManager = areaManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		// TODO Auto-generated method stub
		if (args.length > 1) {
			if (args[1].equalsIgnoreCase("all")) {
				areaManager.removeAll();
				sender.sendMessage("All areas removed.");
			}
		} else {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Cuboid area = areaManager.getArea(player.getLocation());
				if (area != null) {
					if (areaManager.remove(area)) {
						player.sendMessage("Area " + area + " removed.");
						return true;
					} else {
						player.sendMessage("Error removing this area.");
						plugin.getLogger().info(
								player + " could not remove area " + area);
					}
				} else {
					player.sendMessage("You are not in a defined area.");
				}
			}
		}
		return false;
	}
}
