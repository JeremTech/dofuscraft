package fr.dofuscraft.dofuscraftstuffs.events;

import com.mojang.realmsclient.gui.ChatFormatting;
import fr.dofuscraft.dofuscraftcore.blocks.DCCropBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.text.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Locale;

public class CancelPlantsEvent
{
    @SubscribeEvent
    public void onCancel(BlockEvent.BreakEvent e)
    {
        IBlockState state = e.getState();

        if (state.getBlock() instanceof DCCropBlock)
        {
            DCCropBlock b = (DCCropBlock) state.getBlock();

            if (b.getMetaFromState(state) < 3 && !e.getPlayer().isCreative())
            {
                if (!e.getWorld().isRemote)
                {
                    ITextComponent text = new TextComponentTranslation("dofuscraft.plant.break.error", 140);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
                e.setCanceled(true);
            }
        }
    }
}
