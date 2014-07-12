package com.forgeessentials.permissions.commands;

import com.forgeessentials.permissions.ExportThread;
import com.forgeessentials.util.ChatUtils;
import com.forgeessentials.util.OutputHandler;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandFEPermExport {
    public static void processCommandPlayer(EntityPlayer sender, String[] args)
    {
        String output = "export";
        if (args.length > 1)
        {
            ChatUtils.sendMessage(sender, "Improper syntax. Please try this instead: " + " /feperm export [folderName]");
            return;
        }
        else if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("help"))
            {
                ChatUtils.sendMessage(sender, "Improper syntax. Please try this instead: " + " /feperm export [folderName]");
                return;
            }
            else
            {
                output = args[0];
            }
        }

        OutputHandler.chatConfirmation(sender, " {PermSQL} Starting permissions export...");
        startThread(sender, output);
    }

    public static void processCommandConsole(ICommandSender sender, String[] args)
    {
        String output = "export";
        if (args.length > 1)
        {
            ChatUtils.sendMessage(sender, "Improper syntax. Please try this instead: " + " /feperm export [folderName]");
            return;
        }
        else if (args.length == 1)
        {
            if (args[0].equalsIgnoreCase("help"))
            {
                ChatUtils.sendMessage(sender, "Improper syntax. Please try this instead: " + " /feperm export [folderName]");
                return;
            }
            else
            {
                output = args[0];
            }
        }

        ChatUtils.sendMessage(sender, " {PermSQL} Starting permissions export...");
        startThread(sender, output);
    }

    private static void startThread(ICommandSender sender, String output)
    {
        ExportThread t = new ExportThread(output, sender);
        t.run();
    }

}
