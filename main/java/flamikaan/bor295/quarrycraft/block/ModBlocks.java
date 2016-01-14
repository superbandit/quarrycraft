package flamikaan.bor295.quarrycraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import flamikaan.bor295.quarrycraft.tileEntity.AdvancedQuarryTileEntity;

public class ModBlocks 
{
	public static Block advancedQuarryUpgrade;
	public static Block goldBlock;
	public static Block advancedQuarry;
	
	
	
	public static final void init()
	{
		
		
		GameRegistry.registerBlock(advancedQuarryUpgrade = new BlockQuarryUpgrade("quarryUpgrade"), ItemBlockMeta.class, "quarryUpgrade");
		GameRegistry.registerBlock(goldBlock = new GoldBlock("goldBlock"), "goldBlock");
		GameRegistry.registerBlock(advancedQuarry = new AdvancedQuarry("advancedQuarry"), "advancedQuarry");
		
		GameRegistry.registerTileEntity(AdvancedQuarryTileEntity.class, "advancedQuarryTileEntity");
		
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.goldBlock, 8), new Object[] {"&#&",  '#', Blocks.stone , '&', Items.gold_ingot});
		
	}
	public static final void postInit()
	{
		//ItemStack EnderiumBlock = GameRegistry.findItemStack("ThermalFoundation", "blockEnderium", 1);
		//ItemStack SignalumBlock = GameRegistry.findItemStack("ThermalFoundation", "blockSignalum", 1);
		
		//GameRegistry.addRecipe(new ItemStack(ModBlocks.advancedQuarry), new Object[] {"&%&", "!#$", "@^@",  '#', Blocks.redstone_torch , '&', Items.nether_star , '%', new ItemStack(advancedQuarryUpgrade, 1, 0), '!', new ItemStack(advancedQuarryUpgrade, 1, 1), '$', new ItemStack(advancedQuarryUpgrade, 1, 2), '^', new ItemStack(advancedQuarryUpgrade, 1, 3), '@', EnderiumBlock });
		GameRegistry.addRecipe(new ItemStack(ModBlocks.advancedQuarryUpgrade, 1, 0), new Object[] {"#@#", "@%@","#@#",  '#', Blocks.diamond_block, '@', Blocks.iron_block, '%', Items.sugar});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.advancedQuarryUpgrade, 1, 1), new Object[] {"#@#", "@%@","#@#",  '#', Blocks.diamond_block, '@', Blocks.iron_block, '%', Items.cake});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.advancedQuarryUpgrade, 1, 2), new Object[] {"#@#", "@%@","#@#",  '#', Blocks.diamond_block, '@', Blocks.iron_block, '%', new ItemStack(advancedQuarryUpgrade, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.advancedQuarryUpgrade, 1, 3), new Object[] {"#@#", "@%@","#@#",  '#', Blocks.diamond_block, '@', Blocks.iron_block, '%', Blocks.lapis_block});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.advancedQuarryUpgrade, 1, 4), new Object[] {"#@#", "@%@","#@#",  '#', Blocks.diamond_block, '@', Blocks.iron_block, '%', Blocks.gold_block});
	}
}
