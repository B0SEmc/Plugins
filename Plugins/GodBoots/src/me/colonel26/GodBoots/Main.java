package me.colonel26.GodBoots;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener{

	@Override
	public void onEnable() {
	//startup
		this.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
	//shutdown
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// /gb
		if (label.equalsIgnoreCase("godboots") || label.equalsIgnoreCase("gb")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Deso mais la console peut pas fly :P");
				return true;
			}
			Player player = (Player) sender;
			if (player.getInventory().firstEmpty() == -1) {
				Location loc = player.getLocation();
				World world = player.getWorld();
				world.dropItemNaturally(loc, getitem());
				player.sendMessage(ChatColor.RED + "Vos Bottes sont tombees par terre");
			}
			player.getInventory().addItem(getitem());
			player.sendMessage(ChatColor.GOLD + "Vous avez recu les bottes");
			return true;
		}
		return false;
	}
	
	public ItemStack getitem() {
		
		ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta meta = boots.getItemMeta();
		
		meta.setDisplayName(ChatColor.GOLD + "Boots Incroyables");
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.GOLD + "Elles sont incroyables !");
		meta.setLore(lore);
		
		meta.addEnchant(Enchantment.PROTECTION_FALL, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.setUnbreakable(true);
		
		boots.setItemMeta(meta);
		
		return boots;
	}
	
	@EventHandler
	public void creatureSpawn(CreatureSpawnEvent event) {
		if (event.getEntityType() == EntityType.ZOMBIE) {
			Zombie zombie = (Zombie) event.getEntity();
			
			zombie.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
			zombie.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
			zombie.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
			zombie.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
			zombie.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10000, 50));
			double randomNumber = Math.random();
			if (randomNumber >= 0 && randomNumber < 0.2) {
				zombie.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
			}
		}
	}
	
	@EventHandler
	public void onJump(PlayerMoveEvent event) {
		Player player = (Player) event.getPlayer();
		if (player.getInventory().getBoots() != null)
			if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Boots Incroyables"))
				if (player.getInventory().getBoots().getItemMeta().hasLore())
					if(event.getFrom().getY() < event.getTo().getY() && player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR)
						player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(1));
		}
	
	@EventHandler
	public void SpeedEffect(PlayerMoveEvent event) {
		Player player = (Player) event.getPlayer();
		if (player.getInventory().getBoots() != null)
			if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Boots Incroyables"))
				if (player.getInventory().getBoots().getItemMeta().hasLore()) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 11));
					player.setFoodLevel(20);
					player.setSaturation(20);
				}
			}
	
	@EventHandler
	public void onFall (EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (event.getCause() == DamageCause.FALL) {
				if (player.getInventory().getBoots() != null)
					if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Boots Incroyables"))
						if (player.getInventory().getBoots().getItemMeta().hasLore()) {
							event.setCancelled(true);
						}
			}
		}
	}
	
}
