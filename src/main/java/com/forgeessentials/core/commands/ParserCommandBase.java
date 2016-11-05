package com.forgeessentials.core.commands;

import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import com.forgeessentials.commons.CommandParserArgs;
import com.forgeessentials.util.FeCommandParserArgs;

public abstract class ParserCommandBase extends ForgeEssentialsCommandBase
{

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        FeCommandParserArgs arguments = new FeCommandParserArgs(this, args, sender);
        try
        {
            parse(arguments);
        }
        catch (CommandParserArgs.CancelParsingException e)
        {
            /* do nothing */
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args)
    {
        FeCommandParserArgs arguments = new FeCommandParserArgs(this, args, sender, true);
        try
        {
            parse(arguments);
        }
        catch (CommandException e)
        {
            return arguments.tabCompletion;
        }
        return arguments.tabCompletion;
    }

    public abstract void parse(FeCommandParserArgs arguments);

}
