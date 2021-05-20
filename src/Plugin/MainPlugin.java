package Plugin;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

public class MainPlugin extends JavaPlugin{
	
	private static MainPlugin PLUGIN;
	
	@Override
	public void onEnable() {
		PLUGIN = this;
		
		CustomEnchantHandler.register();
		//maybe move listener to separate Listener package instead of having in ench
		getServer().getPluginManager().registerEvents(new NimbleEnchantment(), this);
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getServer().getPluginManager().registerEvents(new EconomyHandler(), this);
		getCommand("bal").setExecutor(new EconomyHandler());
		getCommand("pay").setExecutor(new EconomyHandler());
		getCommand("shop").setExecutor(new ShopHandler());
		getServer().getPluginManager().registerEvents(new MenuHandler(), this);
	}
	
	
	@Override
	public void onDisable() {
		
	}
	
	public static MainPlugin getPlugin() {
		return PLUGIN;
	}
	
	
}
