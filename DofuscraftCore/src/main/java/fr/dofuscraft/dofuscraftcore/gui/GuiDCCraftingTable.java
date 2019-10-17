package fr.dofuscraft.dofuscraftcore.gui;

import fr.dofuscraft.dofuscraftcore.container.DCCraftingTableContainer;
import fr.dofuscraft.dofuscraftcore.crafting_manager.DCCraftingManager;
import fr.dofuscraft.dofuscraftcore.crafting_manager.DCShapedRecipes;
import fr.dofuscraft.dofuscraftcore.crafting_manager.IDCRecipe;
import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.block.BlockChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.Sys;

import java.awt.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class GuiDCCraftingTable extends GuiContainer
{
    private ResourceLocation texture = new ResourceLocation(References.MODID, "textures/gui/dc_crafting_table.png");

    public String job;
    public String level;
    public String check = "false";
    public float alpha = 0;

    public GuiDCCraftingTable(InventoryPlayer invPlayer, World world, BlockPos pos)
    {

        super(new DCCraftingTableContainer(invPlayer, world, pos));
        this.xSize = 211; //La largeur du gui en pixels (supprimez-le pour laisser celle par défaut)
        this.ySize = 191; //La hauteur du gui en pixels (supprimez-le pour laisser celle par défaut)
    }

    /**
     * Fonction pour dessiner le premier plan
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        fontRenderer.drawString(I18n.format("container.crafting"), 110 + (48 - fontRenderer.getStringWidth(I18n.format("container.crafting"))) + (fontRenderer.getStringWidth(I18n.format("container.crafting")) / 2), 13, 0xFFFFFF); //On dessine le "titre" du gui, le I18n.format va traduire le texte donné, n'oubliez pas de l'ajouter dans votre fichier de langues !

        if (check.equalsIgnoreCase("true"))
        {
            fontRenderer.drawString(I18n.format("dc.container.craftingtable.job") + " " + I18n.format("job." + job), 115, 36, 0xffffff);
            fontRenderer.drawString(I18n.format("dc.container.craftingtable.level") + " " + level, 115, 50, 0x66ff66);
        } else if (check.equalsIgnoreCase("nolvl"))
        {
            fontRenderer.drawString(I18n.format("dc.container.craftingtable.job") + " " + I18n.format("job." + job), 115, 36, 0xffffff);
            fontRenderer.drawString(I18n.format("dc.container.craftingtable.level") + " " + level, 115, 50, 0xff0000);
        } else if (check.equalsIgnoreCase("false"))
        {
            fontRenderer.drawString(I18n.format("dc.container.craftingtable.job"), 115, 36, 0xffffff);
            fontRenderer.drawString(I18n.format("dc.container.craftingtable.level"), 115, 50, 0xffffff);
        }

        //Color color = new Color(0, 0, 0, alpha);
        //fontRenderer.drawString(I18n.format("Test de Test"), 115, 70, color.getRGB());
    }

    /**
     * Fonction pour dessiner l'arrière plan
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        mc.getTextureManager().bindTexture(texture); //On bind la texture
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize); //Et on la dessine
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

        alpha = Math.max(0, Math.min(alpha, 10));

    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();

        String correct = DCShapedRecipes.getMatchesS();

        boolean isCrafted = true;

        boolean IsfadeOut = false;

        if (correct != null)
        {
            if (correct.equalsIgnoreCase("true"))
            {
                HashMap<Integer, String> map = getLevelAndJob();
                Integer[] a1 = map.keySet().toArray(new Integer[0]);

                if (a1[0] != null)
                {
                    this.level = "" + a1[0];
                    this.job = map.get(a1[0]);
                    this.check = "true";
                }
            } else if (correct.equalsIgnoreCase("nolvl"))
            {
                HashMap<Integer, String> map = getLevelAndJob();

                if (map != null)
                {

                    Integer[] a1 = map.keySet().toArray(new Integer[0]);

                    if (a1[0] != null)
                    {
                        this.job = map.get(a1[0]);
                        this.level = "" + a1[0];
                        this.check = "nolvl";
                    }
                }
            } else if (correct.equalsIgnoreCase("false"))
            {
                this.job = "";
                this.level = "";
                this.check = "false";
            }
        } else
        {
            this.check = "false";
    }


    }

    public HashMap<Integer, String> getLevelAndJob()
    {
        for (IDCRecipe recipe : DCCraftingManager.getInstance().getRecipeList())
        {
            if (recipe instanceof DCShapedRecipes)
            {
                DCShapedRecipes recipe1 = (DCShapedRecipes) recipe;

                HashMap<Integer, String> map = new HashMap<>();
                //map.put(recipe1.getJobLevelRequire(), recipe1.getJobRequire());
                map.put(DCCraftingManager.getInstance().getLvelRequireP(), DCCraftingManager.getInstance().getJobRequireP());
                return map;
            }
        }
        return null;
    }
}
