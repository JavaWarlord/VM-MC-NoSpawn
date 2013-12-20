package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.command.CommandInterface;
import com.valiantmarauders.minecraft.location.CuboidManager;

public class ListAreas implements CommandInterface {

	private JavaPlugin plugin;
	private CuboidManager cuboidManager;

	public ListAreas(JavaPlugin plugin, CuboidManager cuboidManager) {
		// TODO Auto-generated constructor stub
		this.setPlugin(plugin);
		this.cuboidManager = cuboidManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		// TODO Auto-generated method stub
		cuboidManager.display(sender);
		return false;
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}

	public void setPlugin(JavaPlugin plugin) {
		this.plugin = plugin;
	}
}
