package Plugin;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.enchantments.Enchantment;

public class CustomEnchantHandler {
	
	public static Enchantment NIMBLE = new NimbleEnchantment();
	
	public static void register() {
		//put custom enchantments into array, use for
		boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(NIMBLE);
		
		if(!registered) {
			registerEnchantment(NIMBLE);
		}
	}
	
	public static void registerEnchantment(Enchantment enchantment) {
	    boolean registered = true;
	    try {
	        Field f = Enchantment.class.getDeclaredField("acceptingNew");
	        f.setAccessible(true);
	        f.set(null, true);
	        Enchantment.registerEnchantment(enchantment);
	    } catch (Exception e) {
	        registered = false;
	        e.printStackTrace();
	    }
	    if(registered){
	        // It's been registered!
	    }
	}
}
