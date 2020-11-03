package me.tinkot.namecase;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class CommandBC extends Command {

    CommandBC() {
        super("namecase");
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if ("reload".equals(args[0])) {
                Config.reload();
                sender.sendMessage(new TextComponent("§a[IllegalNameCase]配置文件重载完成"));
                return;
            }
            if ("help".equals(args[0])) {
                sender.sendMessage(new TextComponent("§a[IllegalNameCase]使用:/" + this.getName() + " reload 来重载配置文件"));
                sender.sendMessage(new TextComponent("§a[IllegalNameCase]使用:/" + this.getName() + " version 来查看版本"));
                return;

            }
            if ("version".equals(args[0])) {
                sender.sendMessage(new TextComponent("§a[IllegalNameCase]插件版本:" +Config.version + " 配置文件版本:"+Config.getString("配置文件版本")));
                return;
            }
            sender.sendMessage(new TextComponent("§a[IllegalNameCase]使用:/" + this.getName() + " help 查看帮助"));
        }
    }
}
