package fr.dofuscraft.dofuscraftcore.fluid;

import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidBlueSoulIncraftam extends Fluid
{
    public FluidBlueSoulIncraftam()
    {
        super("blue_soul_incraftam", new ResourceLocation(References.MODID, "fluids/blue_soul_incraftam_still"), new ResourceLocation(References.MODID, "fluids/blue_soul_incraftam_flowing"));
        FluidRegistry.registerFluid(this);
        FluidRegistry.addBucketForFluid(this);
    }
}