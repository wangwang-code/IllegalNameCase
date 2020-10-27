package me.tinkot.namecase;

import net.md_5.bungee.api.CommandSender;
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
                        sender.sendMessage("Config重载完成");
                        return;
                }
                break;
            default:
                break;
        }
        sender.sendMessage("usage:/"+this.getName()+" reload");
    }
}
