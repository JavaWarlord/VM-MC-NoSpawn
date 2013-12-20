package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.command.CommandInterface;

public class ShowAreas implements CommandInterface {
	JavaPlugin plugin;
	AreaManager areaManager;

	public ShowAreas(JavaPlugin plugin, AreaManager areaManager) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
		this.areaManager = areaManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		// TODO Auto-generated method stub
		return false;
	}
}
