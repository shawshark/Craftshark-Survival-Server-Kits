package me.shawshark.survivalkits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class main extends JavaPlugin {
	
	List<Player> usedkit = new ArrayList<Player>();
	
	String prefix = ChatColor.GOLD + "[" + ChatColor.BLUE + "Survival" + ChatColor.GOLD + "]";
	
	String notdonator = prefix + ChatColor.GREEN + " Looks like you are not a donator!";
	String purchasearankhere = prefix + ChatColor.GREEN + " If you wish to use a kit purchase a rank at: " + ChatColor.GOLD + "www.craftshark.net";
	String nopermissionsforkit = prefix + ChatColor.GOLD + " You don't have permissions to use this kit!";
	String hereisyourdonatorkit = prefix + ChatColor.GREEN + " Here is your donator kit, Enjoy!";
	
	public void onEnable() {
	}
	
	public void onDisable() {
		if(usedkit.size() > 0 ) {
			usedkit.clear();
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = (Player)sender;
		PermissionUser user = PermissionsEx.getUser(p);
		
		
		if(command.getName().equalsIgnoreCase("kit")) {
			
			if(args.length < 1) {
				p.sendMessage(prefix + ChatColor.GREEN + " Incorrect usage!");
				p.sendMessage(prefix + ChatColor.GREEN + " /kit [kit name]");
				
				if(user.inGroup("default")) {
					p.sendMessage(purchasearankhere);
				}
				
			} else {
				if(!usedkit.contains(p)) {
					
					if(args[0].equalsIgnoreCase("donor"))  {
						
						if(user.inGroup("donor")) {
							
							p.sendMessage(hereisyourdonatorkit);
							usedkit.add(p);
							usedkit(p/*, p.getName()*/);
							// Give donor kit
						
						} else {
							
							p.sendMessage(nopermissionsforkit);
							
							if(user.inGroup("default")) {
								
								p.sendMessage(notdonator);
								p.sendMessage(purchasearankhere);
								
							} else {
								// do nothing.
							}
						}
					}
					
					if(args[0].equalsIgnoreCase("supporter"))  {
						
						if(user.inGroup("supporter")) {
						
							p.sendMessage(hereisyourdonatorkit);
							usedkit.add(p);
							usedkit(p/*, p.getName()*/);
							// Give supporter kit
						
						} else {
							
							p.sendMessage(nopermissionsforkit);
							
							if(user.inGroup("default")) {
								
								p.sendMessage(notdonator);
								p.sendMessage(purchasearankhere);
								
							} else {
								// do nothing.
							}
						}
					}
					
					
				} else { /* if(!usedkit.contains(p); */
					
					p.sendMessage(prefix + ChatColor.GOLD + " Please wait before using your kit again!");
					p.sendMessage(prefix + ChatColor.GOLD + " Cooldown is as follow: " + ChatColor.GREEN + "(1 Hour)");
					
				}

				
				
				
				
			}
			
			
		}
		
		
		return false;
	}
	
	
	public void usedkit(final Player p/*, String player*/) {
		if(!usedkit.contains(p)) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				@Override
				public void run() {
					if(p.isOnline()) {
						p.sendMessage(prefix + ChatColor.GOLD + " You may use another kit now!");
						usedkit.remove(p);
					}
				}
			}, 5000);
		}
	}
	
	
	
}
