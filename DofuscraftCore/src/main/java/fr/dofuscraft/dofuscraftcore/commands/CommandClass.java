package fr.dofuscraft.dofuscraftcore.commands;

import com.google.common.collect.Lists;
import fr.dofuscraft.dofuscraftcore.capabilities.classe.DCClassProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.classe.IClass;
import fr.dofuscraft.dofuscraftcore.init.ModItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.CommandBlockBaseLogic;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;

public class CommandClass extends CommandBase
{
    private List<String> classes = Lists.newArrayList("masdaigat", "eniripsa", "feca");

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }



    @Override
    public String getName()
    {
        return "classe";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return I18n.format("dofuscraft.commands.classe.usage");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length == 0 || args.length == 1 || args.length == 2)
        {
            notifyCommandListener(sender, this, "dofuscraft.commands.classe.error");
        }
        else
        {
            //region Get player

            EntityPlayer player = null;

            if(args[1] != null && !args[1].equalsIgnoreCase("@p"))
            {
                player =  server.getPlayerList().getPlayerByUsername(args[1]);
            }
            else if(args[1].equalsIgnoreCase("@p"))
            {
                player = getClosestPlayer(sender, server);
            }

            //endregion

            //region set

            if(args[0].equalsIgnoreCase("set"))
            {
                if(player != null)
                {
                    IClass player_class_cap = player.getCapability(DCClassProvider.CLASS_CAP, null);

                    if(args[2].equalsIgnoreCase("masdaigat"))
                    {
                        int i = 0;
                        boolean find = false;

                        while((!find) && (i < player.inventory.getSizeInventory()))
                        {
                            ItemStack item = player.inventory.getStackInSlot(i);

                            if(item.getItem().getUnlocalizedName().equalsIgnoreCase(player_class_cap.getClassItem()))
                            {
                                player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
                                player.sendPlayerAbilities();

                                find = true;
                            }

                            i++;

                        }

                        player.inventory.setInventorySlotContents(player.inventory.getBestHotbarSlot(), new ItemStack(ModItems.MasdaigatSword));

                        player_class_cap.setID("masdaigat");
                        player_class_cap.setName("Masdaigat");
                        player_class_cap.setClassItem(ModItems.MasdaigatSword.getUnlocalizedName());

                        player_class_cap.sync((EntityPlayerMP) player);

                        notifyCommandListener(sender, this, "dofuscraft.commands.classe.success", new Object[]{args[2], args[1]});
                    }
                    if(args[2].equalsIgnoreCase("eniripsa"))
                    {
                        int i = 0;
                        boolean find = false;

                        while((!find) && (i < player.inventory.getSizeInventory()))
                        {
                            ItemStack item = player.inventory.getStackInSlot(i);

                            if(item.getItem().getUnlocalizedName().equalsIgnoreCase(player_class_cap.getClassItem()))
                            {
                                player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
                                player.sendPlayerAbilities();

                                find = true;
                            }
                            i++;
                        }

                        player.inventory.setInventorySlotContents(player.inventory.getBestHotbarSlot(), new ItemStack(ModItems.Eniripsa));

                        player_class_cap.setID("eniripsa");
                        player_class_cap.setName("Eniripsa");
                        player_class_cap.setClassItem(ModItems.Eniripsa.getUnlocalizedName());

                        player_class_cap.sync((EntityPlayerMP) player);

                        notifyCommandListener(sender, this, "dofuscraft.commands.classe.success", new Object[]{args[2], args[1]});
                    }
                    if(args[2].equalsIgnoreCase("feca"))
                    {
                        int i = 0;
                        boolean find = false;

                        while((!find) && (i < player.inventory.getSizeInventory()))
                        {
                            ItemStack item = player.inventory.getStackInSlot(i);

                            if(item.getItem().getUnlocalizedName().equalsIgnoreCase(player_class_cap.getClassItem()))
                            {
                                player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
                                player.sendPlayerAbilities();

                                find = true;
                            }
                            i++;
                        }

                        player.inventory.setInventorySlotContents(player.inventory.getBestHotbarSlot(), new ItemStack(ModItems.fecaShield));

                        player_class_cap.setID("feca");
                        player_class_cap.setName("Feca");
                        player_class_cap.setClassItem(ModItems.fecaShield.getUnlocalizedName());

                        player_class_cap.sync((EntityPlayerMP) player);

                        notifyCommandListener(sender, this, "dofuscraft.commands.classe.success", new Object[]{args[2], args[1]});
                    }
                    else if (!classes.contains(args[2]))
                    {
                        notifyCommandListener(sender, this, "dofuscraft.commands.classe.iderror", new Object[]{args[2]});
                    }
                }
            }

            //endregion

            //region reset

            else if(args[0].equalsIgnoreCase("reset"))
            {
                if(player != null)
                {
                    IClass player_class_cap = player.getCapability(DCClassProvider.CLASS_CAP, null);

                    int i = 0;
                    boolean find = false;

                    while((!find) && (i < player.inventory.getSizeInventory()))
                    {
                        ItemStack item = player.inventory.getStackInSlot(i);

                        if(item.getItem().getUnlocalizedName().equalsIgnoreCase(player_class_cap.getClassItem()))
                        {
                            player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
                            player.sendPlayerAbilities();

                            find = true;
                        }
                        i++;
                    }

                    player.experienceTotal = 0;
                    player.experience = 0;
                    player.experienceLevel = 0;

                    player_class_cap.setID("");
                    player_class_cap.setName("");
                    player_class_cap.setClassItem("");

                    player_class_cap.sync((EntityPlayerMP) player);
                    player.sendPlayerAbilities();



                    notifyCommandListener(sender, this, "dofuscraft.commands.classe.reset", new Object[]{args[1]});
                }
            }
            else
            {
                //notifyCommandListener(sender, this, "dofuscraft.commands.classe.error");
            }

            //endregion
        }
    }


    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        switch (args.length)
        {
            case 1:
                return Lists.newArrayList("set", "reset");

            case 2:
                return Lists.newArrayList(server.getPlayerList().getOnlinePlayerNames());

            case 3:
                return this.classes;

            default:
                return Lists.newArrayList();
        }
    }

    private EntityPlayer getClosestPlayer(ICommandSender sender, MinecraftServer server)
    {
        String name = sender.getEntityWorld().getClosestPlayer(sender.getPosition().getX(), sender.getPosition().getY(), sender.getPosition().getZ(), 25d, false).getName();
        EntityPlayer player = server.getPlayerList().getPlayerByUsername(name);

        return player;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index)
    {
        return args.length == 2 && args[1] != null;
    }
}
