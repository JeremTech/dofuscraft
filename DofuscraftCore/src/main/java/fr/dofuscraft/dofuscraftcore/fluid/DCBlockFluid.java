package fr.dofuscraft.dofuscraftcore.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DCBlockFluid extends BlockFluidClassic
{
    public DCBlockFluid(String name, Fluid fluid, Material material)
    {
        super(fluid, material);
        setRegistryName(name);
        setUnlocalizedName(getRegistryName().toString());
        GameRegistry.findRegistry(Block.class).register(this);
    }

    @SideOnly(Side.CLIENT)
    public void render()
    {
        ModelLoader.setCustomStateMapper(this,new StateMap.Builder().ignore(LEVEL).build());
    }
}