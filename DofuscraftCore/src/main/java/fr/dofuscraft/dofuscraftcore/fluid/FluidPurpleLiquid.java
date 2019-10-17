package fr.dofuscraft.dofuscraftcore.fluid;

import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;


public class FluidPurpleLiquid extends Fluid
{
    public FluidPurpleLiquid()
    {
        super("purple_liquid", new ResourceLocation(References.MODID, "fluids/purple_fluid_still"), new ResourceLocation(References.MODID, "fluids/purple_fluid_flowing"));
        FluidRegistry.registerFluid(this);
        FluidRegistry.addBucketForFluid(this);
    }
}