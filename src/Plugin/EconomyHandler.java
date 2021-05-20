package Plugin;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.md_5.bungee.api.ChatColor;

public class EconomyHandler implements Listener, CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub

		Player p;
		if (sender instanceof Player) {
			p = (Player) sender;
		} else {
			return false;
		}
		switch (cmd.getName()) {
		case "bal":
			return handleBal(p, args);

		case "pay":
			return handlePay(p, args);
		}

		return true;

	}
	
	@EventHandler
	public void serverCommand(ServerCommandEvent e) {
		String cmd = e.getCommand();
		String[] args = cmd.split(" ");
		if(args[0].equals("pay")) {
			if(args.length != 3) {
				return;
			}
			Player target = Bukkit.getPlayerExact(args[1]);
			if(!(target instanceof Player)) {
				return;
			}
			try {
				double amt = Double.parseDouble(args[2]);
				PersistentDataContainer data2 = target.getPersistentDataContainer();
				double targetBal = data2.get(new NamespacedKey(MainPlugin.getPlugin(), "balance"),
						PersistentDataType.DOUBLE);
				targetBal += amt;
				data2.set(new NamespacedKey(MainPlugin.getPlugin(), "balance"), PersistentDataType.DOUBLE, targetBal);
				System.out.println("$"+amt+" given to "+target.getName());
				target.sendMessage("$"+amt+" received from the console!");
			} catch (NumberFormatException e2) {
				e2.printStackTrace();
				return;
			}
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!p.getPersistentDataContainer().has(new NamespacedKey(MainPlugin.getPlugin(), "balance"),
				PersistentDataType.DOUBLE)) {
			p.getPersistentDataContainer().set(new NamespacedKey(MainPlugin.getPlugin(), "balance"),
					PersistentDataType.DOUBLE, 0.0);
		}
	}

	public boolean handleBal(Player p, String[] args) {
		PersistentDataContainer data = p.getPersistentDataContainer();
		if (args.length == 0) {
			double balance = data.get(new NamespacedKey(MainPlugin.getPlugin(), "balance"), PersistentDataType.DOUBLE);
			p.sendMessage(ChatColor.GOLD + "Balance: " + ChatColor.GRAY + balance);
		}
		return true;
	}

	public boolean handlePay(Player p, String[] args) {
		PersistentDataContainer data = p.getPersistentDataContainer();
		if (args.length == 2) {
			Player target = Bukkit.getPlayerExact(args[0]);
			if (!(target instanceof Player)) {
				p.sendMessage("Can't find player");
				return true;
			}
			if(target.getName().equals(p.getName())) {
				p.sendMessage("You can't pay yourself");
				return true;
			}
			PersistentDataContainer data2 = target.getPersistentDataContainer();
			try {
				double amt = Double.parseDouble(args[1]);
				double playerBal = data.get(new NamespacedKey(MainPlugin.getPlugin(), "balance"),
						PersistentDataType.DOUBLE);
				double targetBal = data2.get(new NamespacedKey(MainPlugin.getPlugin(), "balance"),
						PersistentDataType.DOUBLE);
				if (playerBal < amt) {
					p.sendMessage("You don't have enough!");
					return true;
				}
				playerBal -= amt;
				targetBal += amt;
				data.set(new NamespacedKey(MainPlugin.getPlugin(), "balance"), PersistentDataType.DOUBLE, playerBal);
				data2.set(new NamespacedKey(MainPlugin.getPlugin(), "balance"), PersistentDataType.DOUBLE, targetBal);
				p.sendMessage("You just paid "+target.getName()+" $"+amt+"!");
				target.sendMessage("You received $"+amt+" from"+p.getName()+"!");
			} catch (NumberFormatException e) {
				p.sendMessage("Invalid amount");
				return true;
			}

		}

		return false;

	}

}
