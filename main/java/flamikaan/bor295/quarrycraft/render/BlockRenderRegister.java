package flamikaan.bor295.quarrycraft.render;

import flamikaan.bor295.quarrycraft.Main;
import flamikaan.bor295.quarrycraft.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockRenderRegister 
{
	public static String modid = Main.MODID;
	
	public static void preInit()
	{
		ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.advancedQuarryUpgrade), new ModelResourceLocation("quarrycraft:quarryUpgrade_0", "inventory"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.advancedQuarryUpgrade), new ModelResourceLocation("quarrycraft:quarryUpgrade_1", "inventory"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.advancedQuarryUpgrade), new ModelResourceLocation("quarrycraft:quarryUpgrade_2", "inventory"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.advancedQuarryUpgrade), new ModelResourceLocation("quarrycraft:quarryUpgrade_3", "inventory"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.advancedQuarryUpgrade), new ModelResourceLocation("quarrycraft:quarryUpgrade_4", "inventory"));
	}
	
	public static void registerBlockRenderer()
	{
		reg(ModBlocks.goldBlock);
		reg(ModBlocks.advancedQuarry);
	    regWithMeta(ModBlocks.advancedQuarryUpgrade, 0, "quarryUpgrade_0");
	    regWithMeta(ModBlocks.advancedQuarryUpgrade, 1, "quarryUpgrade_1");
	    regWithMeta(ModBlocks.advancedQuarryUpgrade, 2, "quarryUpgrade_2");
	    regWithMeta(ModBlocks.advancedQuarryUpgrade, 3, "quarryUpgrade_3");
	    regWithMeta(ModBlocks.advancedQuarryUpgrade, 4, "quarryUpgrade_4");
	}
	
	public static void reg(Block block)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(modid + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}
	
	public static void regWithMeta(Block block, int meta, String file)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(Item.getItemFromBlock(block), meta, new ModelResourceLocation(modid + ":" + file, "inventory"));
	}
}
