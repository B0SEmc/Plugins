package me.colonel26.godarmor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
        if (label.equalsIgnoreCase("godarmor") || label.equalsIgnoreCase("ga")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Deso mais la console peut pas recevoir d item :P");
                return true;
            }
            Player player = (Player) sender;
            if (player.getInventory().firstEmpty() == -1) {
                Location loc = player.getLocation();
                World world = player.getWorld();
                world.dropItemNaturally(loc, getitem());
                world.dropItemNaturally(loc, getitem1());
                world.dropItemNaturally(loc, getitem2());
                world.dropItemNaturally(loc, getitem3());
                player.sendMessage(ChatColor.RED + "Votre armure est tombees par terre");
            }
            player.getInventory().addItem(getsword());
            player.getInventory().addItem(getitem());
            player.getInventory().addItem(getitem1());
            player.getInventory().addItem(getitem2());
            player.getInventory().addItem(getitem3());
            player.sendMessage(ChatColor.GOLD + "Vous avez recu votre armure");
            return true;
        }
        return false;
    }

    public ItemStack getsword() {

        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = sword.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "God Sword");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GOLD + "Elle est incroyable !");
        meta.setLore(lore);

        meta.addEnchant(Enchantment.DAMAGE_ALL, 150, true);
        meta.addEnchant(Enchantment.FIRE_ASPECT, 20, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addEnchant(Enchantment.DURABILITY, 500, true);

        sword.setItemMeta(meta);

        return sword;
    }

    public ItemStack getitem() {

        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = boots.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "God Boots");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GOLD + "Elles sont incroyables !");
        meta.setLore(lore);

        meta.addEnchant(Enchantment.PROTECTION_FALL, 1, true);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addEnchant(Enchantment.DURABILITY, 500, true);

        boots.setItemMeta(meta);

        return boots;
    }

    public ItemStack getitem1() {

        ItemStack LEGGINGS = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta meta = LEGGINGS.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "God Leggings");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GOLD + "Ils sont incroyables !");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addEnchant(Enchantment.DURABILITY, 500, true);

        LEGGINGS.setItemMeta(meta);

        return LEGGINGS;
    }
    public ItemStack getitem2() {

        ItemStack CHESTPLATE = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = CHESTPLATE.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "God Chestplate");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GOLD + "Il est incroyables !");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 20, true);
        meta.addEnchant(Enchantment.DIG_SPEED, 4, true);
        meta.addEnchant(Enchantment.THORNS, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addEnchant(Enchantment.DURABILITY, 500, true);

        CHESTPLATE.setItemMeta(meta);

        return CHESTPLATE;
    }
    public ItemStack getitem3() {

        ItemStack Helmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta meta = Helmet.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "God Helmet");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GOLD + "Il sont incroyables !");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addEnchant(Enchantment.DURABILITY, 500, true);
        meta.addEnchant(Enchantment.OXYGEN, 200, true);
        meta.addEnchant(Enchantment.WATER_WORKER, 1, true);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addEnchant(Enchantment.DURABILITY, 500, true);

        Helmet.setItemMeta(meta);

        Helmet.setItemMeta(meta);

        return Helmet;
    }

    @EventHandler
    public void onJump(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getBoots() != null)
            if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("God Boots"))
                if (player.getInventory().getBoots().getItemMeta().hasLore())
                    if(event.getFrom().getY() < event.getTo().getY() && player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR)
                        player.setVelocity(player.getLocation().getDirection().multiply(2).setY(1));
    }

    @EventHandler
    public void SpeedEffect(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getBoots() != null)
            if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("God Boots"))
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
                    if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("God Boots"))
                        if (player.getInventory().getBoots().getItemMeta().hasLore()) {
                            event.setCancelled(true);
                        }
            }
        }
    }

}
