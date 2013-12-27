package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.command.CommandInterface;
import com.valiantmarauders.minecraft.selection.CuboidSelectionManager;

public class SetNoSpawnArea implements CommandInterface {
	private JavaPlugin plugin;
	private NoSpawnAreaManager noSpawnAreaManager;
	private CuboidSelectionManager selectionManager;

	public SetNoSpawnArea(JavaPlugin plugin, NoSpawnAreaManager noSpawnAreaManager,
			CuboidSelectionManager selectionManager) {
		super();
		this.plugin = plugin;
		this.noSpawnAreaManager = noSpawnAreaManager;
		this.selectionManager = selectionManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		// TODO Auto-generated method stub
		if (sender instanceof Player) {
			NoSpawnArea area = new NoSpawnArea(selectionManager.getSelection(
					(Player) sender).getPoints());
			sender.sendMessage("Set area: " + area);
			plugin.getLogger().info(sender + " is adding area " + area);
			return noSpawnAreaManager.add((NoSpawnArea) area.clone());
		}
		return false;
	}
}
