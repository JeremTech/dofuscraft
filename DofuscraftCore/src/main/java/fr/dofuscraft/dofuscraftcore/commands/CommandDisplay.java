package fr.dofuscraft.dofuscraftcore.commands;

import com.google.common.collect.Lists;
import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.network.PacketCmdDisplay;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;

public class CommandDisplay extends CommandBase
{
    private List<String> guis = Lists.newArrayList("classSelection", "playerInfo");

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getName()
    {
        return "display";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return I18n.format("dofuscraft.commands.display.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length == 0 || args.length == 1)
        {
            notifyCommandListener(sender, this, "dofuscraft.commands.display.error");
        }
        else
        {
            EntityPlayer player = server.getPlayerList().getPlayerByUsername(args[1]);

            if(args[0].equalsIgnoreCase("classSelection"))
            {
                Dofuscraftcore.network.sendTo(new PacketCmdDisplay(2), (EntityPlayerMP) player);
                notifyCommandListener(sender, this, "dofuscraft.commands.display.success", new Object[]{args[0], args[1]});
            }
            else if(args[0].equalsIgnoreCase("playerInfo"))
            {
                Dofuscraftcore.network.sendTo(new PacketCmdDisplay(3), (EntityPlayerMP) player);
                notifyCommandListener(sender, this, "dofuscraft.commands.display.success", new Object[]{args[0], args[1]});
            }
            else
            {
                notifyCommandListener(sender, this, "dofuscraft.commands.display.guierror", new Object[]{args[0]});
            }
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        switch (args.length)
        {
            case 1:
                return this.guis;

            case 2:
                return Lists.newArrayList(server.getPlayerList().getOnlinePlayerNames());

            default:
                return Lists.newArrayList();
        }
    }
}
