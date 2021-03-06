package me.tinkot.namecase;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.ProxyServer;

public class NameCase extends Plugin implements Listener {

	static NameCase instance;
    {
        instance=this;
    }

	@Override
	public void onEnable()
	{
		Config.reload();
		getProxy().getPluginManager().registerListener(this, new LoginEventListener(this));
		getLogger().info("§aIllegalNameCase已运行");
		getLogger().info("§a作者：Tinkot，删减者：小狗");
		getLogger().info("§a原版https://www.spigotmc.org/resources/namecase.62586/");
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandBC());
	}
    public void onDisable() { getLogger().info("§aIllegalNameCase已卸载"); }
}