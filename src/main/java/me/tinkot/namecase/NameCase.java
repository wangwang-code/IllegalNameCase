package me.tinkot.namecase;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class NameCase extends Plugin implements Listener {
	private static Config config;

	@Override
	public void onEnable() {
		config = new Config(this);
		getProxy().getPluginManager().registerListener(this, new LoginEventListener(config, this));
		getLogger().info("Hello world.");
		getLogger().info("IllegalNameCase已运行");
		getLogger().info("作者：Tinkot，删减者：小狗");
		getLogger().info("原版https://www.spigotmc.org/resources/namecase.62586/");
	}


	public static Config getConfig() {
		return config;
	}
}