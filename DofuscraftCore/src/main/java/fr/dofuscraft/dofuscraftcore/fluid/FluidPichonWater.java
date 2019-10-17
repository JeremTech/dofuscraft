package fr.dofuscraft.dofuscraftcore.fluid;

import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidPichonWater extends Fluid
{
    public FluidPichonWater()
    {
        super("pichon_water", new ResourceLocation(References.MODID, "fluids/pichon_water_still"), new ResourceLocation(References.MODID, "fluids/pichon_water_flowing"));
        FluidRegistry.registerFluid(this);
        FluidRegistry.addBucketForFluid(this);
    }
}