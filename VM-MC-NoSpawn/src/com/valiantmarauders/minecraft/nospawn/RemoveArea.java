package com.valiantmarauders.minecraft.nospawn;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.block.BlockChangeDatabase;
import com.valiantmarauders.minecraft.block.Cuboid;
import com.valiantmarauders.minecraft.command.CommandInterface;
import com.valiantmarauders.minecraft.location.CuboidManager;

public class RemoveArea implements CommandInterface {
	private JavaPlugin plugin;
	private CuboidManager cuboidManager;

	public RemoveArea(JavaPlugin plugin, CuboidManager cuboidManager) {
		super();
		this.plugin = plugin;
		this.cuboidManager = cuboidManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		// TODO Auto-generated method stub
		if (args.length > 1) {
			if (args[1].equalsIgnoreCase("all")) {
				cuboidManager.removeAll();
				sender.sendMessage("All areas removed.");
			}
		} else {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Cuboid area = cuboidManager.get(player.getLocation());
				if (area != null) {
					BlockChangeDatabase blockDB = ((NoSpawn) plugin)
							.getBlockChangeDatabase();
					List<Location> points = area.getPoints();
					for (Location loc : points) {
						blockDB.restore(loc.getBlock());
					}
					if (cuboidManager.remove(area)) {
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
