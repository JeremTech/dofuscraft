package fr.dofuscraft.dofuscraftcore.events;

import fr.dofuscraft.dofuscraftcore.items.DCItemSpell;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class DCPlayerEvent
{
    @SubscribeEvent
    public void onItemToss(ItemTossEvent e)
    {
        if (e.getEntityItem().getItem().getItem() instanceof DCItemSpell)
        {
            e.getPlayer().inventory.addItemStackToInventory(e.getEntityItem().getItem());
            e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void playerConnect(PlayerEvent.PlayerLoggedInEvent e){

        if(e.player.experienceLevel < 1){

            e.player.experienceLevel = 1;

        }

    }

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent e)
    {
        /*if (e.getPhase() == new PlayerInteractEvent.RightClickBlock(e.getEntityPlayer(), e.getHand(), e.getPos(), e.getFace(), e.getEntityPlayer().))
        {
            Block block = e.getEntityPlayer().world.getBlockState(e.getPos()).getBlock();
            if (block == Blocks.CHEST) //Don't know what the last 3 parameters are
            {
               e.getEntityPlayer().sendMessage(new TextComponentString("Bienvenue a toi"));
            }
            e.setCanceled(true);
        }*/
    }

}
