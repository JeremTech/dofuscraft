package fr.dofuscraft.dofuscraftcore.fluid;

import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidIncraftamLakeWater extends Fluid
{
    public FluidIncraftamLakeWater()
    {
        super("incraftam_lake_water", new ResourceLocation(References.MODID, "fluids/incraftam_lake_water_still"), new ResourceLocation(References.MODID, "fluids/incraftam_lake_water_flowing"));
        FluidRegistry.registerFluid(this);
        FluidRegistry.addBucketForFluid(this);
    }
}