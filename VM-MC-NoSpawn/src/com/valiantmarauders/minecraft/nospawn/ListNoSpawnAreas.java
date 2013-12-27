package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.command.CommandInterface;

public class ListNoSpawnAreas implements CommandInterface {

	private JavaPlugin plugin;
	private NoSpawnAreaManager noSpawnAreaManager;

	public ListNoSpawnAreas(JavaPlugin plugin, NoSpawnAreaManager noSpawnAreaManager) {
		// TODO Auto-generated constructor stub
		this.setPlugin(plugin);
		this.noSpawnAreaManager = noSpawnAreaManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		// TODO Auto-generated method stub
		noSpawnAreaManager.display(sender);
		return false;
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}

	public void setPlugin(JavaPlugin plugin) {
		this.plugin = plugin;
	}
}
