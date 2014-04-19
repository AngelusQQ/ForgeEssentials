package com.forgeessentials.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MovingObjectPosition;

import com.forgeessentials.api.permissions.RegGroup;
import com.forgeessentials.commands.util.FEcmdModuleCommands;
import com.forgeessentials.util.FunctionHelper;
import com.forgeessentials.util.OutputHandler;

public class CommandJump extends FEcmdModuleCommands
{

	@Override
	public String getCommandName()
	{
		return "jump";
	}

	@Override
	public String[] getDefaultAliases()
	{
		return new String[]
		{ "j" };
	}

	@Override
	public void processCommandPlayer(EntityPlayer sender, String[] args)
	{
		MovingObjectPosition mo = FunctionHelper.getPlayerLookingSpot(sender, false);
		if (mo == null)
		{
			OutputHandler.chatError(sender, "command.jump.toofar");
			return;
		}
		else
		{
			((EntityPlayerMP) sender).playerNetServerHandler.setPlayerLocation(mo.blockX + .5, mo.blockY + 1, mo.blockZ + .5, sender.rotationPitch, sender.rotationYaw);
		}
	}

	@Override
	public boolean canConsoleUseCommand()
	{
		return false;
	}

	@Override
	public RegGroup getReggroup()
	{
		return RegGroup.ZONE_ADMINS;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/jump Jump.";
	}
}
