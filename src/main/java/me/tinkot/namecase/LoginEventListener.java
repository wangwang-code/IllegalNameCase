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

	LoginEventListener(NameCase plugin) {
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerLogin(final PreLoginEvent event) {
		event.registerIntent(NameCase.instance);
		ProxyServer.getInstance().getScheduler().runAsync(NameCase.instance, () -> {

			Case reason = DEFAULT_CASE;

			String loginName = event.getConnection().getName();

			//String 正则表达式 = config.getString("名称限制"); //尝试 已废弃

			if (!loginName.matches(Config.getString("名称限制"))) {
				reason = Case.ILLEGAL;
			}

			if (reason != Case.PASSED) {
				String message;
				if (reason == Case.ILLEGAL) {
					message = Config.getString("踢出消息");
					NameCase.instance.getLogger().info("§a玩家["+loginName+"]因带有非法字符而被踢出");
				} else {
					message = "unknown";

				}
				message = message.replace("\\n", "\n"); // hotfix for new line character

				event.setCancelReason(new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
				event.setCancelled(true);
			}
			event.completeIntent(NameCase.instance);
		});
	}

}
