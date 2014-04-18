package com.forgeessentials.backup;

import java.io.File;
import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

import com.forgeessentials.api.APIRegistry;
import com.forgeessentials.api.permissions.RegGroup;
import com.forgeessentials.api.permissions.query.PermQueryPlayer;
import com.forgeessentials.core.commands.ForgeEssentialsCommandBase;

public class CommandBackup extends ForgeEssentialsCommandBase
{
	static String		source;
	static String		output;
	static List<String>	fileList;

	@Override
	public String getCommandName()
	{
		return "backup";
	}

	@Override
	public void processCommandPlayer(EntityPlayer sender, String[] args)
	{
		Backup b;
		if (args.length != 1)
		{
			b = new Backup(true);
		}
		else
		{
			if (isInteger(args[0]))
			{
				b = new Backup(parseInt(sender, args[0]), true);
			}
			else
			{
				b = new Backup(new File(args[0]));
			}
		}
		
		if (b != null)
			b.startThread();
	}

	@Override
	public void processCommandConsole(ICommandSender sender, String[] args)
	{
		Backup b = null;
		if (args.length != 1)
		{
			b = new Backup(true);
		}
		else
		{
			if (isInteger(args[0]))
			{
				b = new Backup(parseInt(sender, args[0]), true);
			}
			else
			{
				b = new Backup(new File(args[0]));
			}
		}
		
		if (b != null)
			b.startThread();
	}

	@Override
	public boolean canConsoleUseCommand()
	{
		return true;
	}

	@Override
	public boolean canPlayerUseCommand(EntityPlayer sender)
	{
		return APIRegistry.perms.checkPermAllowed(new PermQueryPlayer(sender, getCommandPerm()));
	}

	@Override
	public String getCommandPerm()
	{
		return "fe.backup";
	}

	public static boolean isInteger(String s)
	{
		try
		{
			Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/backup [dimID|foldername] Make a backup of everything or only specified folder/world.";
	}

	@Override
	public RegGroup getReggroup() {
		// TODO Auto-generated method stub
		return RegGroup.ZONE_ADMINS;
	}
}
