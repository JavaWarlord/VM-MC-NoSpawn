package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.command.CommandInterface;

public class RemoveNoSpawnArea implements CommandInterface {
	private JavaPlugin plugin;
	private NoSpawnAreaManager noSpawnAreaManager;

	public RemoveNoSpawnArea(JavaPlugin plugin, NoSpawnAreaManager noSpawnAreaManager) {
		super();
		this.plugin = plugin;
		this.noSpawnAreaManager = noSpawnAreaManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		// TODO Auto-generated method stub
		if (args.length > 1) {
			if (args[1].equalsIgnoreCase("all")) {
				noSpawnAreaManager.removeAll();
				sender.sendMessage("All areas removed.");
			}
		} else {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				NoSpawnArea area = noSpawnAreaManager.getArea(player.getLocation());
				if (area != null) {
					if (noSpawnAreaManager.remove(area)) {
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
