package me.tinkot.namecase;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class CommandBC extends Command {

    CommandBC() {
        super("namecase");
    }

    public void execute(CommandSender sender, String[] args)
    {
        switch(args.length)
        {
            case 1:
                switch (args[0])
                {
                    case "reload":
                        LoginEventListener.config = new Config(NameCase.instance);
                        sender.sendMessage(new TextComponent("§a[IllegalNameCase]配置文件重载完成"));
                        return;
                }
                break;
            default:
                break;
        }
        sender.sendMessage(new TextComponent("§a[IllegalNameCase]使用:/"+this.getName()+" reload 来重载配置文件"));
    }
}
