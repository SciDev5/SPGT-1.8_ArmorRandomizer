package scidev.armorRandomizer.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import scidev.armorRandomizer.ArmorRandomizer;

public class ToggleCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		String errorPrefix = "§c- ArmorRandomizer:ERROR - ";
		String successPrefix = "§a- ArmorRandomizer:OK - ";
		if (args.length != 1) {
			sender.sendMessage(errorPrefix+"Incorrect usage, use: /"+alias+" <enabled>");
			return true;
		}
		if (args[0].equalsIgnoreCase("true")) {
			if (!ArmorRandomizer.enabled) {
				ArmorRandomizer.instance.start();
				sender.sendMessage(successPrefix+"Enabled plugin.");
			} else {
				sender.sendMessage(errorPrefix+"Already enabled.");
			}
		} else {
			if (ArmorRandomizer.enabled) {
				ArmorRandomizer.instance.stop();
				sender.sendMessage(successPrefix+"Disabled plugin.");
			} else {
				sender.sendMessage(errorPrefix+"Already disabled.");
			}
		}
		return true;
	}

}
