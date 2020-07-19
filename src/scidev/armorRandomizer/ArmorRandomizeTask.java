package scidev.armorRandomizer;

import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class ArmorRandomizeTask extends BukkitRunnable {

	private Random rand;
	private static final Material[] armorBootsTypes = new Material[]{
			Material.DIAMOND_BOOTS,
			Material.IRON_BOOTS,
			Material.GOLD_BOOTS,
			Material.CHAINMAIL_BOOTS,
			Material.LEATHER_BOOTS,
			Material.AIR
		};
	private static final Material[] armorLeggingsTypes = new Material[]{
			Material.DIAMOND_LEGGINGS,
			Material.IRON_LEGGINGS,
			Material.GOLD_LEGGINGS,
			Material.CHAINMAIL_LEGGINGS,
			Material.LEATHER_LEGGINGS,
			Material.AIR
		};
	private static final Material[] armorChestplateTypes = new Material[]{
			Material.DIAMOND_CHESTPLATE,
			Material.IRON_CHESTPLATE,
			Material.GOLD_CHESTPLATE,
			Material.CHAINMAIL_CHESTPLATE,
			Material.LEATHER_CHESTPLATE,
			Material.AIR
		};
	private static final Material[] armorHelmetTypes = new Material[]{
			Material.DIAMOND_HELMET,
			Material.IRON_HELMET,
			Material.GOLD_HELMET,
			Material.CHAINMAIL_HELMET,
			Material.LEATHER_HELMET,
			Material.AIR
		};
	private static final Enchantment[] enchantmentTypes = new Enchantment[]{
			Enchantment.PROTECTION_ENVIRONMENTAL,
			Enchantment.PROTECTION_EXPLOSIONS,
			Enchantment.PROTECTION_FIRE,
			Enchantment.PROTECTION_PROJECTILE,
			Enchantment.THORNS,
			Enchantment.DURABILITY,
		};
	private static final int enchantMaxLevel = 10;
	private static final int maxEnchants = 4;
	
	public ArmorRandomizeTask() {
		rand = new Random();
	}
	
	@Override
	public void run() {
		List<Player> players = ArmorRandomizer.instance.getServer().getWorld("world").getPlayers();
		ArmorRandomizer.instance.getServer().broadcastMessage(ChatColor.GRAY+"["+ChatColor.GOLD+"!"+ChatColor.GRAY+"]"+ChatColor.WHITE+" Randomizing armor!");
		for (Player plr : players) {
			ItemStack[] newArmor = new ItemStack[4];
			for (int i = 0; i < newArmor.length; i++) {
				newArmor[i] = randomItem(i);
			}
			plr.getInventory().setArmorContents(newArmor);
		}
	}

	public ItemStack randomItem(int n) {
		ItemStack item = null;
		switch (n) {
		case 3: item = new ItemStack(armorHelmetTypes[rand.nextInt(armorHelmetTypes.length)]); break;
		case 2: item = new ItemStack(armorLeggingsTypes[rand.nextInt(armorLeggingsTypes.length)]); break;
		case 1: item = new ItemStack(armorChestplateTypes[rand.nextInt(armorChestplateTypes.length)]); break;
		case 0: item = new ItemStack(armorBootsTypes[rand.nextInt(armorBootsTypes.length)]); break;
		}
		if (item.getType() == Material.AIR) return item;
		ItemMeta meta = item.getItemMeta();
		for (int i = 0; i < rand.nextInt(maxEnchants); i++)
			meta.addEnchant(enchantmentTypes[rand.nextInt(enchantmentTypes.length)], rand.nextInt(enchantMaxLevel)+1, true);
        item.setItemMeta(meta);
		return item;
	}
	
}
