package fr.dofuscraft.dofuscraftcore.fluid;

import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidMerdroiteWater extends Fluid
{
    public FluidMerdroiteWater()
    {
        super("merdroite_water", new ResourceLocation(References.MODID, "fluids/merdroite_water_still"), new ResourceLocation(References.MODID, "fluids/merdroite_water_flowing"));
        FluidRegistry.registerFluid(this);
        FluidRegistry.addBucketForFluid(this);
    }
}