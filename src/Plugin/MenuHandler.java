package Plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuHandler implements Listener {

	@EventHandler
	public void onShopClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getView().getTitle().equals("Shop")) {
			e.setCancelled(true);
			ItemStack curItem = e.getCurrentItem();
			if(curItem == null) {
				return;
			}
			if(curItem.getType().equals(Material.DIAMOND)) {
				p.sendMessage("sell");
				handleSell(p);
			} else if (curItem.getType().equals(Material.DIAMOND_HELMET)) {
				p.sendMessage("buy");
				handleBuy(p);
			}
		} else if(e.getView().getTitle().equals("Sell")) {
			e.setCancelled(true);
			ItemStack curItem = e.getCurrentItem();
			if(curItem == null) {
				return;
			}
			if(curItem.getType().equals(Material.WRITTEN_BOOK)) {
				ShopHandler.handleMain(p);
			}
		} else if(e.getView().getTitle().equals("Buy")) {
			e.setCancelled(true);
			ItemStack curItem = e.getCurrentItem();
			if(curItem == null) {
				return;
			}
			if(curItem.getType().equals(Material.WRITTEN_BOOK)) {
				ShopHandler.handleMain(p);
			}
		}
	}
	
	public void handleSell(Player p) {
		Inventory gui = Bukkit.createInventory(p, 9, "Sell");
		
		ItemStack sellItem = new ItemStack(Material.DIAMOND);
		ItemMeta sellMeta = sellItem.getItemMeta();
		sellMeta.setDisplayName("§f§1Sell");
		sellItem.setItemMeta(sellMeta);
		
		ItemStack back = new ItemStack(Material.WRITTEN_BOOK);
		
		gui.setItem(0, back);
		gui.setItem(2, sellItem);
		p.openInventory(gui);
	}
	
	public void handleBuy(Player p) {
		Inventory gui = Bukkit.createInventory(p, 9, "Sell");
		
		ItemStack sellItem = new ItemStack(Material.DIAMOND);
		ItemMeta sellMeta = sellItem.getItemMeta();
		sellMeta.setDisplayName("§f§1Buy");
		sellItem.setItemMeta(sellMeta);
		
		ItemStack back = new ItemStack(Material.WRITTEN_BOOK);
		
		gui.setItem(0, back);
		gui.setItem(2, sellItem);
		p.openInventory(gui);
	}
}
