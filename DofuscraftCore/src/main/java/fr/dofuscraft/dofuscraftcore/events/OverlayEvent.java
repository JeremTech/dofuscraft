package fr.dofuscraft.dofuscraftcore.events;

import fr.dofuscraft.dofuscraftcore.items.DCItemSpell;
import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.awt.*;

@Mod.EventBusSubscriber(modid = References.MODID, value = {Side.CLIENT})
public class OverlayEvent
{

    public static ResourceLocation spellIcon = new ResourceLocation(References.MODID, "textures/gui/spells_2.png");

    public static ResourceLocation icon = new ResourceLocation(References.MODID, "textures/gui/icons.png");

    public static String spellText = "";

    @SubscribeEvent
    public static void renderGameOverlayPre(RenderGameOverlayEvent.Pre event)
    {

    }

    @SubscribeEvent
    public static void renderGameOverlayPost(RenderGameOverlayEvent.Post event)
    {

        Minecraft MC = Minecraft.getMinecraft();
        NBTTagCompound compound = null;
        if(MC.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof DCItemSpell) {

            DCItemSpell stack = (DCItemSpell) MC.player.getHeldItem(EnumHand.MAIN_HAND).getItem();
            compound = MC.player.getHeldItem(EnumHand.MAIN_HAND).getTagCompound();

            if (event.getType().equals(RenderGameOverlayEvent.ElementType.ALL)) {

                int width = event.getResolution().getScaledWidth();


                MC.getTextureManager().bindTexture(spellIcon);
                Gui.drawScaledCustomSizeModalRect(event.getResolution().getScaledWidth() - 50, event.getResolution().getScaledHeight() - 35, 0, 0, 64, 64, 32, 32, 64, 64);

                double cooldown = 0.0D;

                if(compound != null && compound.getIntArray("cooldownList")[compound.getInteger("currentSpell")] > 0){


                    int cs = stack.spellList[compound.getInteger("currentSpell")].GetCooldown();

                    int diffUT = (compound.getIntArray("cooldownList")[compound.getInteger("currentSpell")]+cs) - (int)(System.currentTimeMillis()/1000L);

                    int lul = (int) ((System.currentTimeMillis()/1000L) - compound.getIntArray("cooldownList")[compound.getInteger("currentSpell")]);

                    if (lul > 0 && diffUT >= 0 && lul/(double)cs > 0)
                    {
                        cooldown = (lul/(double)cs);

                    }

                }

                MC.getTextureManager().bindTexture(icon);
                if(compound != null && compound.getIntArray("cooldownList")[compound.getInteger("currentSpell")] > 0 && (1 - cooldown) < 1)
                {
                    Gui.drawScaledCustomSizeModalRect(event.getResolution().getScaledWidth() - 47, event.getResolution().getScaledHeight() - 32, 206, 0, 49, 49, 26, (int) (26 * (1 - cooldown)), 256, 256);

                }

                MC.fontRenderer.drawString(I18n.format("spell." + spellText + ".name"), event.getResolution().getScaledWidth() - 55 - MC.fontRenderer.getStringWidth(I18n.format("spell." + spellText + ".name")), event.getResolution().getScaledHeight() - 20, Color.WHITE.getRGB());

            }

        }

    }





}
