package me.tinkot.namecase;


import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class LoginEventListener implements Listener {
	enum Case {
		PASSED, ILLEGAL
	}

	private static final Case DEFAULT_CASE = Case.PASSED;
	private Config config;
	private NameCase plugin;

	LoginEventListener(Config config, NameCase plugin) {
		this.config = config;
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerLogin(final PreLoginEvent event) {
		event.registerIntent(plugin);
		ProxyServer.getInstance().getScheduler().runAsync(plugin, new Runnable() {
			public void run() {

				Case reason = DEFAULT_CASE;

				String loginName = event.getConnection().getName();


				if (!loginName.matches("[a-zA-Z0-9_]*")) {
					reason = Case.ILLEGAL;
				}

				if (reason != Case.PASSED) {
					String message;
					if (reason == Case.ILLEGAL) {
						message = config.getString("ILLEGAL_CHARS");
						plugin.getLogger().info("玩家["+loginName+"]因带有非法字符而被踢出");
					} else {
						message = "unknown";

					}
					message = message.replace("\\n", "\n"); // hotfix for new line character

					event.setCancelReason(new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
					event.setCancelled(true);
				}
				event.completeIntent(plugin);
			}
		});
	}
}
