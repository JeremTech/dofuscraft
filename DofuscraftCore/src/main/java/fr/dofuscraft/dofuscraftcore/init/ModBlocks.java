package fr.dofuscraft.dofuscraftcore.init;

import com.google.common.collect.Lists;
import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.blocks.vanilla.*;
import fr.dofuscraft.dofuscraftcore.utils.Utils;
import fr.dofuscraft.dofuscraftcore.blocks.*;
import fr.dofuscraft.dofuscraftcore.utils.References;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

public class ModBlocks
{
	public static final ModBlocks INSTANCE = new ModBlocks();

	// Blocks
	public static Block pink_leaves, brick_bonta, brick_brakmar, invisible_light, stone_slab_block, sandstone_slab_block, dofuscraft_crafting_table;

	// Stairs
	public static DCStairs bonta_stairs, brakmar_stairs, gold_stairs;

	// Slabs
	public static DCHalfSlab bonta_slab_half, brakmar_slab_half, gold_slab_half;
	public static DCDoubleSlab bonta_slab_double, brakmar_slab_double, gold_slab_double;

	// Crops
	public static Block wheat, barley, oats, hop, flax, rye, hemp;

	// Plants
	public static Block small_nettle, nettle, small_edelweiss, edelweiss, small_five_leaf_clover, five_leaf_clover, small_freyesque_orchid, freyesque_orchid, small_sage, sage, small_wild_mint, wild_mint;

	// Ores
	public static Block iron_ore, copper_ore, bronze_ore, kobalt_ore, tin_ore;

	// Ore Regenerators
	public static Block iron_regen, copper_regen, bronze_regen, kobalt_regen, tin_regen;

	// Logs
	public static Block log_ash;

	public static DCBlockDispenser dispenser, dropper;

	public static DCBlockFurnace furnace, furnace_lit;

	public static DCBlockHooper hopper;

	public static DCBlockChest dcBlockChest;

	public static DCBlockDoubleChest dcBlockDoubleChest;

	// LOgs regenerators
	public static Block log_ash_regen;

	private List<Block> blocks;

	public void init()
	{
		blocks = Lists.newArrayList();

		// Blocks
		pink_leaves = new DCLeavesBlock("pink_leaves");
		brick_bonta = new DCRockBlock("brick_bonta");
		brick_brakmar = new DCRockBlock("brick_brakmar");
		invisible_light = new DCInvisibleLight("invisible_light");
		stone_slab_block = new DCRockBlock("stone_slab_block");
		sandstone_slab_block = new DCRockBlock("sandstone_slab_block");
		dofuscraft_crafting_table = new DCCraftingTable("dofuscraft_crafting_table");

		// Stairs
		brakmar_stairs = new DCStairs("brakmar_stairs", brick_brakmar.getDefaultState());
		bonta_stairs = new DCStairs("bonta_stairs", brick_bonta.getDefaultState());
		gold_stairs = new DCStairs("gold_stairs", Blocks.GOLD_BLOCK.getDefaultState());

		// Slabs
		bonta_slab_half = new DCHalfSlab("bonta_slab_half", Material.ROCK);
		bonta_slab_double = new DCDoubleSlab("bonta_slab_double", Material.ROCK, bonta_slab_half);
		brakmar_slab_half = new DCHalfSlab("brakmar_slab_half", Material.ROCK);
		brakmar_slab_double = new DCDoubleSlab("brakmar_slab_double", Material.ROCK, brakmar_slab_half);
		gold_slab_half = new DCHalfSlab("gold_slab_half", Material.IRON);
		gold_slab_double = new DCDoubleSlab("gold_slab_double", Material.IRON, gold_slab_half);

		// Crops
		wheat = new DCCropBlock("dofuscraft_wheat", ModItems.wheat, 5,new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.55D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.96D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.96D, 1.0D)});
		barley = new DCCropBlock("dofuscraft_barley", ModItems.barley, 7,new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.55D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.96D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.96D, 1.0D)});
		oats = new DCCropBlock("dofuscraft_oats", ModItems.oats, 9, new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.95D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.7D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.7D, 1.0D)});
		hop = new DCCropBlock("dofuscraft_hop", ModItems.hop, 11, new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.70D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.83D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.96D, 1.0D)});
		flax = new DCCropBlock("dofuscraft_flax", ModItems.rye, 15,new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.16D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.43D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.82D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9D, 1.0D)});
		rye = new DCCropBlock("dofuscraft_rye", ModItems.rye, 15,new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.55D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.96D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.96D, 1.0D)});
		hemp = new DCCropBlock("dofuscraft_hemp", ModItems.hemp, 19, new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.16D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.61D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.72D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.94D, 1.0D)});

		// Plants
		small_nettle = new DCPlantBlock("small_nettle", Material.PLANTS, false, small_nettle, 0);
		nettle = new DCPlantBlock("nettle", Material.PLANTS, true, small_nettle, 10);
		small_edelweiss = new DCPlantBlock("small_edelweiss", Material.PLANTS, false, small_edelweiss, 0);
		edelweiss = new DCPlantBlock("edelweiss", Material.PLANTS, true, small_edelweiss, 30);
		small_five_leaf_clover = new DCPlantBlock("small_five_leaf_clover", Material.PLANTS, false, small_five_leaf_clover, 0);
		five_leaf_clover = new DCPlantBlock("five_leaf_clover", Material.PLANTS, true, small_five_leaf_clover, 18);
		small_freyesque_orchid = new DCPlantBlock("small_freyesque_orchid", Material.PLANTS, false, small_freyesque_orchid, 0);
		freyesque_orchid = new DCPlantBlock("freyesque_orchid", Material.PLANTS, true, small_freyesque_orchid, 26);
		small_sage = new DCPlantBlock("small_sage", Material.PLANTS, false, small_sage, 0);
		sage = new DCPlantBlock("sage", Material.PLANTS, true, small_sage, 14);
		small_wild_mint = new DCPlantBlock("small_wild_mint", Material.PLANTS, false, small_wild_mint, 0);
		wild_mint = new DCPlantBlock("wild_mint", Material.PLANTS, true, small_wild_mint, 22);

		/* Ores */
		iron_ore = new DCOre("iron_ore", Material.ROCK, 5.0f, 30.f, "pickaxe", 1, SoundType.STONE, ModItems.iron, 10);
		copper_ore = new DCOre("copper_ore", Material.ROCK, 5.0f, 30.f, "pickaxe", 1, SoundType.STONE, ModItems.copper, 14);
		bronze_ore = new DCOre("bronze_ore", Material.ROCK, 5.5f, 30.f, "pickaxe", 2, SoundType.STONE, ModItems.bronze, 20);
		kobalt_ore = new DCOre("kobalt_ore", Material.ROCK, 6.5f, 30.f, "pickaxe", 2, SoundType.STONE, ModItems.cobalt, 22);
		tin_ore = new DCOre("tin_ore", Material.ROCK, 7.5f, 30.f, "pickaxe", 1, SoundType.STONE, ModItems.tin, 30);

		/*dispenser = new DCBlockDispenser("dispenser", Material.ROCK, 7.5f, 30.f, "pickaxe", 1, SoundType.STONE);
		dropper = new DCBlockDispenser("dropper", Material.ROCK, 7.5f, 30.f, "pickaxe", 1, SoundType.STONE);*/

		/* Ore Regenerators*/
		iron_regen = new DCBlockRegenerator("iron_regen", Material.IRON, 5.0f, 30.f, "pickaxe", 1, SoundType.STONE, iron_ore);
		copper_regen = new DCBlockRegenerator("copper_regen", Material.IRON, 5.0f, 30.f, "pickaxe", 1, SoundType.STONE, copper_ore);
		bronze_regen = new DCBlockRegenerator("bronze_regen", Material.IRON, 5.0f, 30.f, "pickaxe", 1, SoundType.STONE, bronze_ore);
		kobalt_regen = new DCBlockRegenerator("kobalt_regen", Material.IRON, 5.0f, 30.f, "pickaxe", 1, SoundType.STONE, kobalt_ore);
		tin_regen = new DCBlockRegenerator("tin_regen", Material.IRON, 5.0f, 30.f, "pickaxe", 1, SoundType.STONE, tin_ore);

		// Logs
		log_ash = new DCLog("log_ash", 10);

		// LOgs regenerators
		log_ash_regen = new DCLogRegenerator("log_ash_regen", Material.WOOD, 3.0f, 30.0f, "pickaxe", 1, SoundType.WOOD, log_ash);

		dispenser = new DCBlockDispenser("dc_dispenser");
		dropper = new DCBlockDispenser("dc_dropper");

		furnace = new DCBlockFurnace("dc_furnace", false);
		furnace_lit = new DCBlockFurnace("dc_furnace_lit", true);

		hopper = new DCBlockHooper("dc_hopper");

		dcBlockChest = new DCBlockChest("dc_chest", BlockChest.Type.BASIC);

		//dcBlockDoubleChest = new DCBlockDoubleChest("dc_double_chest", BlockChest.Type.BASIC);

		for ( Block block : blocks )
		{
			ItemBlock ib = new ItemBlock(block);
			ib.setRegistryName(block.getRegistryName());
			GameRegistry.findRegistry(Item.class).register(ib);
		}



		registerBlock(bonta_slab_half, new ItemSlab(bonta_slab_half, bonta_slab_half, bonta_slab_double));
		registerBlock(brakmar_slab_half, new ItemSlab(brakmar_slab_half, brakmar_slab_half, brakmar_slab_double));
		registerBlock(gold_slab_half, new ItemSlab(gold_slab_half, gold_slab_half, gold_slab_double));
		GameRegistry.findRegistry(Block.class).register(bonta_slab_double);
		GameRegistry.findRegistry(Block.class).register(brakmar_slab_double);
		GameRegistry.findRegistry(Block.class).register(gold_slab_double);
	}

	public static void registerBlock(Block block, ItemBlock itemBlock)
	{
		block.setCreativeTab(Dofuscraftcore.blocks);
		GameRegistry.findRegistry(Block.class).register(block);
		GameRegistry.findRegistry(Item.class).register(itemBlock.setRegistryName(block.getRegistryName()));
		Utils.getLogger().info("Register block : " + block.getUnlocalizedName());
	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent e)
	{
		for ( Block block : blocks )
		{
			registerModel(block);
		}

		registerModel(bonta_slab_half);
		registerModel(bonta_slab_double);
		registerModel(brakmar_slab_half);
		registerModel(brakmar_slab_double);
		registerModel(gold_slab_half);
		registerModel(gold_slab_double);
	}

	private void registerModel(Block block)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(References.MODID, block.getUnlocalizedName().substring(5)), "inventory"));
        Utils.getLogger().info("Register model for block : " + block.getUnlocalizedName());
	}

	public List<Block> getBlocks()
	{
		return blocks;
	}
}
