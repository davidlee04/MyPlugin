package Plugin;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class JoinListener implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS, 1);
		boots.addUnsafeEnchantment(CustomEnchantHandler.NIMBLE, 0);
		ItemMeta meta = boots.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.LIGHT_PURPLE + "Nimble I");
		meta.setLore(lore);
		boots.setItemMeta(meta);
		p.getEquipment().setBoots(boots);
		
		
	}

}
