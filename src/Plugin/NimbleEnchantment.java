package Plugin;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NimbleEnchantment extends EnchantmentWrapper implements Listener {
	
	public final int maxLvl;
	public final String name;
	
	public NimbleEnchantment() {
		super("nimble");
		maxLvl = 1;
		name = "Nimble";
	}
	
	@EventHandler
	public void onArmorWear(InventoryCloseEvent e) {
		HumanEntity human = e.getPlayer();
		Player p;
		if(human instanceof Player) {
			p = Bukkit.getPlayer(human.getName());
		} else {
			return;
		}
		if(p.getEquipment().getBoots() != null && p.getEquipment().getBoots().getItemMeta().hasEnchant(CustomEnchantHandler.NIMBLE)) {
			Bukkit.broadcastMessage("before:"+p.getWalkSpeed());
			addSpeed(p, p.getEquipment().getBoots().getItemMeta().getEnchantLevel(CustomEnchantHandler.NIMBLE));
			Bukkit.broadcastMessage("after:"+p.getWalkSpeed());
		} else {
			Bukkit.broadcastMessage("before2"+p.getWalkSpeed());
			removeSpeed(p);			
			Bukkit.broadcastMessage("after2"+p.getWalkSpeed());
		}
		Bukkit.broadcastMessage("in");
	}
	
	@EventHandler
	public void onRightClickArmor(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getHand() == EquipmentSlot.HAND && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			if(e.getItem().getItemMeta().hasEnchant(CustomEnchantHandler.NIMBLE) && p.getEquipment().getBoots() == null) {
				Bukkit.broadcastMessage("before3"+p.getWalkSpeed());
				addSpeed(p, e.getItem().getItemMeta().getEnchantLevel(CustomEnchantHandler.NIMBLE));
				Bukkit.broadcastMessage("after3"+p.getWalkSpeed());
			}
		}
	}
	
	private void addSpeed(Player p, int level) {
		p.setWalkSpeed((float) (0.2+.1+.05*level));
		Bukkit.broadcastMessage(level+"");
	}
	
	private void removeSpeed(Player p) {
		p.setWalkSpeed((float) 0.2);
	}


	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		// TODO Auto-generated method stub
		return EnchantmentTarget.ARMOR_FEET;
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return maxLvl;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getStartLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCursed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTreasure() {
		// TODO Auto-generated method stub
		return false;
	}
}
