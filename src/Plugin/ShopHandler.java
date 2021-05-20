package Plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopHandler implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p;
		if(sender instanceof Player) {
			p = (Player) sender;
		} else {
			return false;
		}
		handleMain(p);
		return true;
	}
	
	public static void handleMain(Player p) {
		Inventory gui = Bukkit.createInventory(p, 9, "Shop");
		
		ItemStack sellItem = new ItemStack(Material.DIAMOND);
		ItemMeta sellMeta = sellItem.getItemMeta();
		sellMeta.setDisplayName("§f§1Sell");
		sellItem.setItemMeta(sellMeta);
		
		ItemStack buyItem = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta buyMeta = buyItem.getItemMeta();
		buyMeta.setDisplayName("§f§1Buy");
		buyMeta.addEnchant(CustomEnchantHandler.NIMBLE, 1, false);
		buyItem.setItemMeta(buyMeta);
		
		gui.setItem(2, sellItem);
		gui.setItem(6, buyItem);
		p.openInventory(gui);
	}
	
}
