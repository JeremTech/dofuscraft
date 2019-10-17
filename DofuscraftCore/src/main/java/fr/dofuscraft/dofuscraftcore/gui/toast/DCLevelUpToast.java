package fr.dofuscraft.dofuscraftcore.gui.toast;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.gui.toasts.RecipeToast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class DCLevelUpToast implements IToast {

    private long firstDrawTime;
    private boolean hasNewOutputs;

    public Visibility draw(GuiToast toastGui, long delta)
    {
        if (this.hasNewOutputs)
        {
            this.firstDrawTime = delta;
            this.hasNewOutputs = false;
        }


            toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            toastGui.drawTexturedModalRect(0, 0, 0, 32, 160, 32);
            toastGui.getMinecraft().fontRenderer.drawString(I18n.format("toast.level_up.title") + " " + "10", 30, 7, -11534256);
            toastGui.getMinecraft().fontRenderer.drawString(I18n.format("toast.level_up.description"), 30, 18, -16777216);
            RenderHelper.enableGUIStandardItemLighting();
            return delta - this.firstDrawTime >= 5000L ? IToast.Visibility.HIDE : IToast.Visibility.SHOW;

    }
}
