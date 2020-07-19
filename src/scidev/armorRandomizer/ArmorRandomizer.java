package scidev.armorRandomizer;

import org.bukkit.plugin.java.JavaPlugin;

import scidev.armorRandomizer.commands.ToggleCommand;

public class ArmorRandomizer extends JavaPlugin {
	private ArmorRandomizeTask randomizeTask = null;
	
	public static ArmorRandomizer instance = null;
	public static boolean enabled = false;
	
	@Override
	public void onEnable() {
		instance = this;
		registerCommands();
	}

	public void stop() {
		enabled = false;
		unregisterTasks();
	}
	public void start() {
		enabled = true;
		registerTasks();
	}
	
	private void registerTasks() {
		if (randomizeTask != null)
			unregisterTasks();
		randomizeTask = new ArmorRandomizeTask();
		randomizeTask.runTaskTimer(this, 0, 1*60*20);
	}
	
	private void unregisterTasks() {
		if (randomizeTask == null) 
			return;
		randomizeTask.cancel();
		randomizeTask = null;
	}
	
	private void registerCommands() {
		getServer().getPluginCommand("startscenario_armorrandomizer").setExecutor(new ToggleCommand());
		getServer().getPluginCommand("startscenario_armorrandomizer").setPermission("armorrandomizer.command.toggle.use");
	}
}
