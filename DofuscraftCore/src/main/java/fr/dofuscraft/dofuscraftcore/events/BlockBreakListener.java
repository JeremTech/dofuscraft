package fr.dofuscraft.dofuscraftcore.events;

import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockBreakListener
{
    @SubscribeEvent
    @SuppressWarnings("RedundantMethodOverride")
    public void onHarvestEvent(BlockEvent.BreakEvent e)
    {
        Block target = e.getState().getBlock();
        ILevel level = e.getPlayer().getCapability(DCLevelProvider.LEVEL_CAP, null);

        if (!e.getPlayer().isCreative())
        {
            //region Mineur
            if (target == ModBlocks.copper_ore)
            {
                if (level.getMinerLevel() < 20)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.miner.break.error", 20);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }
            if (target == ModBlocks.bronze_ore)
            {
                if (level.getMinerLevel() < 40)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.miner.break.error", 40);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }
            if (target == ModBlocks.kobalt_ore)
            {
                if (level.getMinerLevel() < 60)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.miner.break.error", 60);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }
            if (target == ModBlocks.tin_ore)
            {
                if (level.getMinerLevel() < 100)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.miner.break.error", 100);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }
            //endregion

            //region Bûcheron
            // Rien pour l'instant
            //endregion

            //region Alchimiste
            if (target == ModBlocks.sage)
            {
                if (level.getAlchemistLevel() < 20)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.alchemist.break.error", 20);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }
            if (target == ModBlocks.five_leaf_clover)
            {
                if (level.getAlchemistLevel() < 40)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.alchemist.break.error", 40);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }
            if (target == ModBlocks.wild_mint)
            {
                if (level.getAlchemistLevel() < 60)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.alchemist.break.error", 60);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }

            if (target == ModBlocks.freyesque_orchid)
            {
                if (level.getAlchemistLevel() < 80)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.alchemist.break.error", 80);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }

            if (target == ModBlocks.edelweiss)
            {
                if (level.getAlchemistLevel() < 100)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.alchemist.break.error", 100);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }
            //endregion

            //region Paysan
            if (target == ModBlocks.barley)
            {
                if (level.getPeasantLevel() < 20)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.peasant.break.error", 20);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }

            if (target == ModBlocks.oats)
            {
                if (level.getPeasantLevel() < 40)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.peasant.break.error", 40);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }

            if (target == ModBlocks.hop)
            {
                if (level.getPeasantLevel() < 60)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.peasant.break.error", 60);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }

            if (target == ModBlocks.flax)
            {
                if (level.getPeasantLevel() < 80)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.peasant.break.error", 80);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }

            if (target == ModBlocks.rye)
            {
                if (level.getPeasantLevel() < 100)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.peasant.break.error", 100);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }
            if (target == ModBlocks.hemp)
            {
                if (level.getPeasantLevel() < 140)
                {
                    e.setCanceled(true);

                    ITextComponent text = new TextComponentTranslation("dofuscraft.peasant.break.error", 140);
                    text.getStyle().setColor(TextFormatting.DARK_RED);
                    e.getPlayer().sendMessage(text);
                }
            }
            //endregion

        }
    }
}
