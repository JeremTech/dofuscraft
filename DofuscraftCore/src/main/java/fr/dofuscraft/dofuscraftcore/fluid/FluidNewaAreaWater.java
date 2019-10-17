package fr.dofuscraft.dofuscraftcore.fluid;

import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidNewaAreaWater extends Fluid
{
    public FluidNewaAreaWater()
    {
        super("newa_area_water", new ResourceLocation(References.MODID, "fluids/newa_area_water_still"), new ResourceLocation(References.MODID, "fluids/newa_area_water_flowing"));
        FluidRegistry.registerFluid(this);
        FluidRegistry.addBucketForFluid(this);
    }
}