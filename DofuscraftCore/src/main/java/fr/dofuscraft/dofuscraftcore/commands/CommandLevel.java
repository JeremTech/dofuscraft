package fr.dofuscraft.dofuscraftcore.commands;

import com.google.common.collect.Lists;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCSubLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ISubLevel;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nullable;
import java.util.List;

public class CommandLevel extends CommandBase
{
    private List<String> jobs = Lists.newArrayList("alchimiste", "bucheron", "mineur", "paysan", "bijoutier", "tailleur", "cordonnier");

    @Override
    public List<String> getAliases()
    {
        return Lists.newArrayList("niv");
    }

    @Override
    public String getName()
    {
        return "niveau";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return I18n.format("dofuscraft.commands.niveau.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length == 0 || args.length == 1 || args.length == 2 || args.length == 3)
        {
            notifyCommandListener(sender, this, "dofuscraft.commands.niveau.error");
        }
        else if ((args.length == 5))
        {
            EntityPlayer player = null;


            //region Get Player

            if (args[4].equalsIgnoreCase("@p"))
            {
                String name = sender.getEntityWorld().getClosestPlayer(sender.getPosition().getX(), sender.getPosition().getY(), sender.getPosition().getZ(), 25d, false).getName();
                player = server.getPlayerList().getPlayerByUsername(name);

            }
            else
            {
                if (player == null)
                {
                    for (String name : server.getPlayerList().getOnlinePlayerNames())
                    {
                        if (args[4].equalsIgnoreCase(name))
                        {
                            player = server.getPlayerList().getPlayerByUsername(args[4]);
                            break;
                        }
                    }
                }
            }

            //endregion

            ILevel levels = player.getCapability(DCLevelProvider.LEVEL_CAP, null);
            ISubLevel sublevels = player.getCapability(DCSubLevelProvider.SUBLEVEL_CAP, null);

            //region ADD
            if (args[1].equalsIgnoreCase("add"))
            {

                //region LEVELS
                if (args[0].equalsIgnoreCase("niveau"))
                {

                    int lvl = parseInt(args[3], 1);

                    String metier = args[2];

                    if (metier.equalsIgnoreCase("alchimiste"))
                    {
                        levels.setAlchemistLevel(levels.getAlchemistLevel() + lvl);
                    }
                    else if (metier.equalsIgnoreCase("bucheron"))
                    {
                        levels.setLumberjackLevel(levels.getLumberjackLevel() + lvl);
                    }
                    else if (metier.equalsIgnoreCase("mineur"))
                    {
                        levels.setMinerLevel(levels.getMinerLevel() + lvl);
                    }
                    else if (metier.equalsIgnoreCase("paysan"))
                    {
                        levels.setPeasantLevel(levels.getPeasantLevel() + lvl);
                    }
                    else if (metier.equalsIgnoreCase("bijoutier"))
                    {
                        levels.setJewelerLevel(levels.getJewelerLevel() + lvl);
                    }
                    else if (metier.equalsIgnoreCase("tailleur"))
                    {
                        levels.setTailorLevel(levels.getTailorLevel() + lvl);
                    }
                    else if (metier.equalsIgnoreCase("cordonnier"))
                    {
                        levels.setShoeMakerLevel(levels.getShoeMakerLevel() + lvl);
                    }
                    else
                    {
                        notifyCommandListener(sender, this, "dofuscraft.commands.niveau.joberror", new Object[]{metier});
                        return;
                    }

                    levels.sync((EntityPlayerMP) player);
                }
                //endregion

                //region SUBLEVELS
                if (args[0].equalsIgnoreCase("sniveau"))
                {

                    int lvl = parseInt(args[3], 1);

                    String metier = args[2];

                    if (metier.equalsIgnoreCase("alchimiste"))
                    {
                        sublevels.setAlchemistSubLevel(sublevels.getAlchemistSubLevel() + lvl);
                        sublevels.checkLevel((EntityPlayerMP) player, "alchemist");
                    }
                    else if (metier.equalsIgnoreCase("bucheron"))
                    {
                        sublevels.setLumberjackSubLevel(sublevels.getLumberjackSubLevel() + lvl);
                        sublevels.checkLevel((EntityPlayerMP) player, "lumberjack");
                    }
                    else if (metier.equalsIgnoreCase("mineur"))
                    {
                        sublevels.setMinerSubLevel(sublevels.getMinerSubLevel() + lvl);
                        sublevels.checkLevel((EntityPlayerMP) player, "miner");
                    }
                    else if (metier.equalsIgnoreCase("paysan"))
                    {
                        sublevels.setPeasantSubLevel(sublevels.getPeasantSubLevel() + lvl);
                        sublevels.checkLevel((EntityPlayerMP) player, "peasant");
                    }
                    else if (metier.equalsIgnoreCase("bijoutier"))
                    {
                        sublevels.setJewelerSubLevel(sublevels.getJewelerSubLevel() + lvl);
                        sublevels.checkLevel((EntityPlayerMP) player, "jeweler");
                    }
                    else if (metier.equalsIgnoreCase("tailleur"))
                    {
                        sublevels.setTailorSubLevel(sublevels.getTailorSubLevel() + lvl);
                        sublevels.checkLevel((EntityPlayerMP) player, "tailor");
                    }
                    else if (metier.equalsIgnoreCase("cordonnier"))
                    {
                        sublevels.setShoeMakerSubLevel(sublevels.getShoeMakerSubLevel() + lvl);
                        sublevels.checkLevel((EntityPlayerMP) player, "shoemaker");
                    }
                    else
                    {
                        notifyCommandListener(sender, this, "dofuscraft.commands.niveau.joberror", new Object[]{metier});
                        return;
                    }

                    sublevels.sync((EntityPlayerMP) player);
                    levels.sync((EntityPlayerMP) player);
                }
                //endregion

            }
            //endregion

            //region REMOVE

            if (args[1].equalsIgnoreCase("remove"))
            {
                //region LEVELS
                if (args[0].equalsIgnoreCase("niveau"))
                {
                    int lvl = parseInt(args[3], 1);

                    String metier = args[2];

                    if (metier.equalsIgnoreCase("alchimiste"))
                    {
                        levels.setAlchemistLevel(levels.getAlchemistLevel() - lvl);
                    }
                    else if (metier.equalsIgnoreCase("bucheron"))
                    {
                        levels.setLumberjackLevel(levels.getLumberjackLevel() - lvl);
                    }
                    else if (metier.equalsIgnoreCase("mineur"))
                    {
                        levels.setMinerLevel(levels.getMinerLevel() - lvl);
                    }
                    else if (metier.equalsIgnoreCase("paysan"))
                    {
                        levels.setPeasantLevel(levels.getPeasantLevel() - lvl);
                    }
                    else if (metier.equalsIgnoreCase("bijoutier"))
                    {
                        levels.setJewelerLevel(levels.getJewelerLevel() - lvl);
                    }
                    else if (metier.equalsIgnoreCase("tailleur"))
                    {
                        levels.setTailorLevel(levels.getTailorLevel() - lvl);
                    }
                    else if (metier.equalsIgnoreCase("cordonnier"))
                    {
                        levels.setShoeMakerLevel(levels.getShoeMakerLevel() - lvl);
                    }
                    else
                    {
                        notifyCommandListener(sender, this, "dofuscraft.commands.niveau.joberror", new Object[]{metier});
                        return;
                    }
                    levels.sync((EntityPlayerMP) player);
                }
                //endregion


                //region SUBLEVELS
                else if (args[0].equalsIgnoreCase("sniveau"))
                {
                    int lvl = parseInt(args[3], 1);

                    String metier = args[2];

                    if (metier.equalsIgnoreCase("alchimiste"))
                    {
                        sublevels.setAlchemistSubLevel(sublevels.getAlchemistSubLevel() - lvl);
                    }
                    else if (metier.equalsIgnoreCase("bucheron"))
                    {
                        sublevels.setLumberjackSubLevel(sublevels.getLumberjackSubLevel() - lvl);
                    }
                    else if (metier.equalsIgnoreCase("mineur"))
                    {
                        sublevels.setMinerSubLevel(sublevels.getMinerSubLevel() - lvl);
                    }
                    else if (metier.equalsIgnoreCase("paysan"))
                    {
                        sublevels.setPeasantSubLevel(sublevels.getPeasantSubLevel() - lvl);
                    }
                    else if (metier.equalsIgnoreCase("bijoutier"))
                    {
                        sublevels.setJewelerSubLevel(sublevels.getJewelerSubLevel() - lvl);
                    }
                    else if (metier.equalsIgnoreCase("tailleur"))
                    {
                        sublevels.setTailorSubLevel(sublevels.getTailorSubLevel() - lvl);
                    }
                    else if (metier.equalsIgnoreCase("cordonnier"))
                    {
                        sublevels.setShoeMakerSubLevel(sublevels.getShoeMakerSubLevel() - lvl);
                    }
                    else
                    {
                        notifyCommandListener(sender, this, "dofuscraft.commands.niveau.joberror", new Object[]{metier});
                        return;
                    }
                    sublevels.sync((EntityPlayerMP) player);
                }
                //endregion
            }
            //endregion

            TextComponentTranslation commandparameter;
            TextComponentTranslation plural;


            if (args[1].equalsIgnoreCase("add"))
            {
                if (parseInt(args[3], 1) == 1)
                {
                    commandparameter = new TextComponentTranslation("dofuscraft.commands.niveau.addone");

                    if (args[0].equalsIgnoreCase("niveau"))
                    {
                        plural = new TextComponentTranslation("dofuscraft.commands.niveau.niveauone");
                    }
                    else
                    {
                        plural = new TextComponentTranslation("dofuscraft.commands.niveau.sousniveauone");
                    }
                }
                else
                {
                    commandparameter = new TextComponentTranslation("dofuscraft.commands.niveau.add");

                    if (args[0].equalsIgnoreCase("niveau"))
                    {
                        plural = new TextComponentTranslation("dofuscraft.commands.niveau.niveau");
                    }
                    else
                    {
                        plural = new TextComponentTranslation("dofuscraft.commands.niveau.sousniveau");
                    }
                }
            }
            else
            {
                if (parseInt(args[3], 1) == 1)
                {
                    commandparameter = new TextComponentTranslation("dofuscraft.commands.niveau.removeone");

                    if (args[0].equalsIgnoreCase("niveau"))
                    {
                        plural = new TextComponentTranslation("dofuscraft.commands.niveau.niveauone");
                    }
                    else
                    {
                        plural = new TextComponentTranslation("dofuscraft.commands.niveau.sousniveauone");
                    }
                }
                else
                {
                    commandparameter = new TextComponentTranslation("dofuscraft.commands.niveau.remove");

                    if (args[0].equalsIgnoreCase("niveau"))
                    {
                        plural = new TextComponentTranslation("dofuscraft.commands.niveau.niveau");
                    }
                    else
                    {
                        plural = new TextComponentTranslation("dofuscraft.commands.niveau.sousniveau");
                    }
                }
            }

            if (args[0].equalsIgnoreCase("niveau"))
            {
                notifyCommandListener(sender, this, "dofuscraft.commands.niveau.success", new Object[]{args[3], plural, commandparameter, args[4], args[2]});
            }
            else if (args[0].equalsIgnoreCase("sniveau"))
            {
                notifyCommandListener(sender, this, "dofuscraft.commands.sousniveau.success", new Object[]{args[3], plural, commandparameter, args[4], args[2]});
            }
            else
            {
                notifyCommandListener(sender, this, "dofuscraft.commands.niveau.error");
                return;
            }
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {

        if (sender instanceof EntityPlayer)
        {
            return sender.getServer().getOpPermissionLevel() == 4;
        }
        if (sender.getServer().getOpPermissionLevel() == 4)
        {
            return true;
        }
        return false;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        switch (args.length)
        {
            case 1:
                return Lists.newArrayList("niveau", "sniveau");

            case 2:
                return Lists.newArrayList("add", "remove");

            case 3:
                return this.jobs;

            case 5:
                return Lists.newArrayList(server.getPlayerList().getOnlinePlayerNames());

            default:
                return Lists.newArrayList();
        }
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index)
    {
        return args.length == 5 && args[4] != null;
    }

    @Override
    public int compareTo(ICommand o)
    {
        return 0;
    }
}



