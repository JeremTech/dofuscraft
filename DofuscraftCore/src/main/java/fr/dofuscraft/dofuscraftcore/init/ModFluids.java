package fr.dofuscraft.dofuscraftcore.init;

import fr.dofuscraft.dofuscraftcore.fluid.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModFluids
{
    // Purple Liquid
    public static FluidPurpleLiquid fluid_purple_liquid;
    public static DCBlockFluid block_purple_liquid;

    // Blue Soul Incraftam Liquid
    public static FluidBlueSoulIncraftam blue_soul_incraftam_liquid;
    public static DCBlockFluid block_blue_soul_incraftam_liquid;

    // Merdroite water
    public static FluidMerdroiteWater merdroite_water;
    public static DCBlockFluid block_merdroite_water;

    // Pichon water
    public static FluidPichonWater pichon_water;
    public static DCBlockFluid block_pichon_water;

    // newa area water
    public static FluidNewaAreaWater newa_area_water;
    public static DCBlockFluid block_newa_area_water;

    // Incraftam lake water
    public static FluidIncraftamLakeWater incraftam_lake_water;
    public static DCBlockFluid block_incraftam_lake_water;

    public static void registerFluids()
    {
        // Purple Liquid
        fluid_purple_liquid = new FluidPurpleLiquid();
        block_purple_liquid = new DCBlockFluid("purple_liquid", fluid_purple_liquid, Material.WATER);

        // Blue Soul Incraftam Liquid
        blue_soul_incraftam_liquid = new FluidBlueSoulIncraftam();
        block_blue_soul_incraftam_liquid = new DCBlockFluid("blue_soul_incraftam", blue_soul_incraftam_liquid, Material.WATER);

        // Merdroite water
        merdroite_water = new FluidMerdroiteWater();
        block_merdroite_water = new DCBlockFluid("merdroite_water", merdroite_water, Material.WATER);

        // Pichon water
        pichon_water = new FluidPichonWater();
        block_pichon_water = new DCBlockFluid("pichon_water", pichon_water, Material.WATER);

        // newa area water
        newa_area_water = new FluidNewaAreaWater();
        block_newa_area_water = new DCBlockFluid("newa_area_water", newa_area_water, Material.WATER);

        // Incraftam lake water
        incraftam_lake_water = new FluidIncraftamLakeWater();
        block_incraftam_lake_water = new DCBlockFluid("incraftam_lake_water", incraftam_lake_water, Material.WATER);
    }

    @SideOnly(Side.CLIENT)
    public static void renderFluids()
    {
        block_purple_liquid.render();
        block_blue_soul_incraftam_liquid.render();
        block_merdroite_water.render();
        block_pichon_water.render();
        block_newa_area_water.render();
        block_incraftam_lake_water.render();
    }
}
