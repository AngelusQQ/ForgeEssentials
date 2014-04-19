package com.forgeessentials.commands;

import java.util.HashSet;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet20NamedEntitySpawn;
import net.minecraft.server.MinecraftServer;

import com.forgeessentials.api.permissions.RegGroup;
import com.forgeessentials.commands.util.FEcmdModuleCommands;
import com.forgeessentials.util.OutputHandler;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class CommandVanish extends FEcmdModuleCommands
{
    public static final String TAGNAME = "vanish";

    public static HashSet<Integer> vanishedPlayers = new HashSet<Integer>();
    
    @Override
    public String getCommandName()
    {
        return "vanish";
    }

    @Override
    public RegGroup getReggroup()
    {
        return RegGroup.OWNERS;
    }

    @Override
    public void processCommandPlayer(EntityPlayer sender, String[] args)
    {
        NBTTagCompound tag = sender.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
        tag.setBoolean(TAGNAME, !tag.getBoolean(TAGNAME));
        sender.getEntityData().setCompoundTag(EntityPlayer.PERSISTED_NBT_TAG, tag);
        
        if (tag.getBoolean(TAGNAME))
        {
            OutputHandler.chatConfirmation(sender, "You are vanished now.");
            vanishedPlayers.add(sender.entityId);
        }
        else
        {
            OutputHandler.chatConfirmation(sender, "You are un vanished now.");
            vanishedPlayers.remove(sender.entityId);
            
            for (Object fakePlayer : MinecraftServer.getServer().worldServers[sender.dimension].playerEntities)
                if (fakePlayer != sender)
                    PacketDispatcher.sendPacketToPlayer(new Packet20NamedEntitySpawn(sender), (Player) fakePlayer);
        }
    }

    @Override
    public boolean canConsoleUseCommand()
    {
        return false;
    }

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/vanish Makes yourself invisible";
	}
}
