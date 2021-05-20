package Plugin;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

public abstract class EnchantmentWrapper extends Enchantment {
	
	public EnchantmentWrapper(String namespace) {
		super(new NamespacedKey(MainPlugin.getPlugin(), namespace));
	}

}
