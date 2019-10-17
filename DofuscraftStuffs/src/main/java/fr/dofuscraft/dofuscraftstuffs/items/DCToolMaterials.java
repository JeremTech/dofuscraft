package fr.dofuscraft.dofuscraftstuffs.items;

import com.google.common.collect.Sets;
import fr.dofuscraft.dofuscraftcore.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

import java.util.HashSet;
import java.util.Set;

public class DCToolMaterials
{
	public static final Item.ToolMaterial ash = EnumHelper.addToolMaterial("ash", 3, 999, 2.0F, 1.0F, 1);
	public static final Item.ToolMaterial iron = EnumHelper.addToolMaterial("iron", 3, 999, 4.0F, 1.0F, 1);
	public static final Item.ToolMaterial copper = EnumHelper.addToolMaterial("copper", 3, 999, 6.0F, 1.0F, 1);
	public static final Item.ToolMaterial bronze = EnumHelper.addToolMaterial("bronze", 3, 999, 8.0F, 1.0F, 1);
	public static final Item.ToolMaterial cobalt = EnumHelper.addToolMaterial("cobalt", 3, 999, 12.0F, 1.0F, 1);

    public static final Item.ToolMaterial sickle_ash = EnumHelper.addToolMaterial("ash", 3, 999, 0.4F, 1.0F, 1);
    public static final Item.ToolMaterial sickle_iron = EnumHelper.addToolMaterial("iron", 3, 999, 0.8F, 1.0F, 1);
    public static final Item.ToolMaterial sickle_copper = EnumHelper.addToolMaterial("copper", 3, 999, 1.2F, 1.0F, 1);
    public static final Item.ToolMaterial sickle_bronze = EnumHelper.addToolMaterial("bronze", 3, 999, 1.6F, 1.0F, 1);
    public static final Item.ToolMaterial sickle_cobalt = EnumHelper.addToolMaterial("cobalt", 3, 999, 2.0F, 1.0F, 1);

    public static final Item.ToolMaterial beginner_sword = EnumHelper.addToolMaterial("beginner_sword", 0, 999, 2f, -2f, 10);

	public static final Set<Block> sickle_set = Sets.newHashSet(ModBlocks.nettle, ModBlocks.sage, ModBlocks.five_leaf_clover, ModBlocks.wild_mint, ModBlocks.freyesque_orchid, ModBlocks.edelweiss);
	public static final Set<Block> scythe_set = Sets.newHashSet(ModBlocks.wheat, ModBlocks.barley, ModBlocks.oats, ModBlocks.hop, ModBlocks.flax, ModBlocks.rye);
    public static final Set<Block> axe_set = Sets.newHashSet(ModBlocks.log_ash);
    public static final Set<Block> pickaxe_set = Sets.newHashSet(ModBlocks.iron_ore, ModBlocks.copper_ore, ModBlocks.bronze_ore, ModBlocks.kobalt_ore, ModBlocks.tin_ore);
}
