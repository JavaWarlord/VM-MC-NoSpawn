package com.valiantmarauders.minecraft.nospawn;

import org.bukkit.Material;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.valiantmarauders.minecraft.command.CommandHandler;

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

	public AreaManager getAreaManager() {
		return areaManager;
	}

	public void setAreaManager(AreaManager areaManager) {
		this.areaManager = areaManager;
	}

	// private MobSpawnListener msl;
	// private BlockSelectListener bsl;
	// private HashMap<String, CommandExecutor> commands;
	private SelectionManager selectionManager;

	public void onEnable() {
		// Save a copy of the default config.yml if one is not there
		// this.saveDefaultConfig();
		// commands = new HashMap<String, CommandExecutor>();
		PluginManager pm = this.getServer().getPluginManager();
		// msl = new MobSpawnListener(this);
		// bsl = new BlockSelectListener(this, Material.ARROW,
		// selectionManager);
		pm.registerEvents(new MobSpawnListener(this), this);
		pm.registerEvents(new BlockSelectListener(this, Material.ARROW,
				selectionManager), this);
		areaManager = new AreaManager(this);
		initializeCommands();
	}

	private void initializeCommands() {
		// TODO Auto-generated method stub
		CommandHandler handler = new CommandHandler();
		handler.register("reload", new Reload(this));
		handler.register("show", new ShowAreas(this, areaManager));
		handler.register("set",
				new SetArea(this, areaManager, selectionManager));
		getCommand("nosp").setExecutor(handler);
		getCommand("nospawn").setExecutor(handler);
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
	// @Override
	// public boolean onCommand(CommandSender sender, Command command,
	// String label, String[] args) {
	// if (command.getName().equalsIgnoreCase("nospawn")
	// || command.getName().equalsIgnoreCase("nosp")) {
	// CommandExecutor cmdEx = commands.get(args[0]);
	// if (cmdEx != null) {
	// return cmdEx.onCommand(sender, command, label, args);
	// }
	// }
	// return false;
	// }

	// } else if (args[0].equalsIgnoreCase("set")) {
	// areaManager.add(new CubedArea(
	// getServer().getWorld("world"), bsl
	// .getSelectedBlock(1).getLocation(), bsl
	// .getSelectedBlock(2).getLocation()));
	// return true;
	// }
	// } else if (args.length == 2) {
	// if (args[0].equalsIgnoreCase("pos")) {
	// int index = 0;
	// try {
	// index = Integer.valueOf(args[1]);
	// } catch (NumberFormatException e) {
	// sender.sendMessage("/nosp pos [1 or 2]");
	// }
	// if (index < 1 || index > 2) {
	// return false;
	// }
	// if (sender instanceof Player) {
	// Location playerLoc = ((Player) sender).getLocation();
	// playerLoc.subtract(0, 1, 0);
	// bsl.setSelectedBlock(index, playerLoc.getBlock());
	// sender.sendMessage("Position " + index + " set at "
	// + playerLoc);
	// }
	// }
	// } else if (args.length > 1) {
	// if (args[0].equalsIgnoreCase("set")) {
	// World world = getServer().getWorld("world");
	// CubedArea newArea = new CubedArea(world,
	// Integer.valueOf(args[1]), Integer.valueOf(args[2]),
	// Integer.valueOf(args[3]), Integer.valueOf(args[4]),
	// Integer.valueOf(args[5]), Integer.valueOf(args[6]));
	// areaManager.add(newArea);
	// sender.sendMessage("Added " + newArea);
	// return true;
	// }
	// }
	// }
	// return false;
	// }
}
