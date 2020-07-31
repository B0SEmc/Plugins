package fr.fairyjeux.godswordplugin;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class CommandGodSword implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player)sender;
			player.getInventory().clear();				
			ItemStack customSword = new ItemStack(Material.DIAMOND_SWORD, 1);
			ItemMeta customM = customSword.getItemMeta();
			customM.setDisplayName(ChatColor.DARK_AQUA + "GodSword");
			customM.setLore(Arrays.asList("The GodSword is a sword for admin ;)","Left click : kill the player", "Right Click : Open Gui for Admin :p"));
			customM.addEnchant(Enchantment.DAMAGE_ALL, 600, true);
			customM.addEnchant(Enchantment.FIRE_ASPECT, 600, true);
			customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			customSword.setItemMeta(customM); 
			player.getInventory().setItemInMainHand(customSword);
			player.updateInventory();
			player.sendMessage(ChatColor.AQUA + "Your GodSword as gived !");
		}
		
		return false;
	}

}