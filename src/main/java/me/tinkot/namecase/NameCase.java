package me.tinkot.namecase;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.ProxyServer;

public class NameCase extends Plugin implements Listener {
    static NameCase instance;
    {
        instance=this;
    }

	private static Config config;

	@Override
	public void onEnable() {
		config = new Config(this);
		getProxy().getPluginManager().registerListener(this, new LoginEventListener(config, this));
		getLogger().info("§aHello world.");
		getLogger().info("§aIllegalNameCase已运行");
		getLogger().info("§a作者：Tinkot，删减者：小狗");
		getLogger().info("§a原版https://www.spigotmc.org/resources/namecase.62586/");
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandBC());
	}
	public static Config getConfig() {
		return config;
	}
}