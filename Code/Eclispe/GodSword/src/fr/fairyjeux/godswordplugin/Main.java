package fr.fairyjeux.godswordplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		System.out.println("Thank for download GodSword plugin");
		super.onEnable();
	}
	
	
	@Override
	public void onDisable() {
		System.out.println("GoodBye");
		super.onDisable();
	}
	
}
