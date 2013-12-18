package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.location.CubedArea;

/**
 * 
 * @author JavaWarlord
 * @version 0.45
 * 
 *          v0.10 - Working NoSpawn area with constant values.
 * 
 *          v0.20 - Multiple NoSpawn areas
 * 
 *          v0.30 - Read areas from file
 * 
 *          v0.35 - Write areas to file
 * 
 *          v0.40 - Set boundaries of areas from inside game (command)
 * 
 *          v0.45 - Set boundaries of areas from inside game (tool)
 * 
 *          v0.50 - Delete areas
 * 
 *          v0.60 - View areas in game
 * 
 *          v0.70 - Resize areas
 * 
 *          v0.80 - Permissions
 * 
 */
public class NoSpawn extends JavaPlugin {

	private AreaManager areaManager;
	private MobSpawnListener msl;
	private BlockSelectListener bsl;

	public void onEnable() {
		// Save a copy of the default config.yml if one is not there
		// this.saveDefaultConfig();
		PluginManager pm = this.getServer().getPluginManager();
		msl = new MobSpawnListener(this);
		bsl = new BlockSelectListener(this, Material.ARROW);
		pm.registerEvents(msl, this);
		pm.registerEvents(bsl, this);
		areaManager = new AreaManager(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("nospawn")
				|| cmd.getName().equalsIgnoreCase("nosp")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("reload")) {
					getServer().getPluginManager().disablePlugin(this);
					getServer().getPluginManager().enablePlugin(this);
					return true;
				} else if (args[0].equalsIgnoreCase("test")) {
					getServer().broadcastMessage(
							"This is just a test command!!!");
					return true;
				} else if (args[0].equalsIgnoreCase("show")) {
					areaManager.display(sender);
					return true;
				} else if (args[0].equalsIgnoreCase("set")) {
					areaManager.add(new CubedArea(
							getServer().getWorld("world"), bsl
									.getSelectedBlock(1).getLocation(), bsl
									.getSelectedBlock(2).getLocation()));
					return true;
				}
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("pos")) {
					int index = 0;
					try {
						index = Integer.valueOf(args[1]);
					} catch (NumberFormatException e) {
						sender.sendMessage("/nosp pos [1 or 2]");
					}
					if (index < 1 || index > 2) {
						return false;
					}
					if (sender instanceof Player) {
						Location playerLoc = ((Player) sender).getLocation();
						playerLoc.subtract(0, 1, 0);
						bsl.setSelectedBlock(index, playerLoc.getBlock());
						sender.sendMessage("Position " + index + " set at "
								+ playerLoc);
					}
				}
			} else if (args.length > 1) {
				if (args[0].equalsIgnoreCase("set")) {
					World world = getServer().getWorld("world");
					CubedArea newArea = new CubedArea(world,
							Integer.valueOf(args[1]), Integer.valueOf(args[2]),
							Integer.valueOf(args[3]), Integer.valueOf(args[4]),
							Integer.valueOf(args[5]), Integer.valueOf(args[6]));
					areaManager.add(newArea);
					sender.sendMessage("Added " + newArea);
					return true;
				}
			}
		}
		return false;
	}

	public void onDisable() {
		areaManager.save();
	}

	public void detectedSpawn(CreatureSpawnEvent event) {
		// TODO Auto-generated method stub
		if (areaManager.contains(event.getEntity().getLocation())) {
			event.setCancelled(true);
		}
	}
}
