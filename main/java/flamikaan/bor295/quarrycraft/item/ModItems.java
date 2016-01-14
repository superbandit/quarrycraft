package flamikaan.bor295.quarrycraft.item;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public final class ModItems 
{	
	public static ToolMaterial DAINKILL = EnumHelper.addToolMaterial("DAINKILL", 3, 32, 150, 50.0F, 10);
	
    public static final void init() 
    {
    	//purpleDust = new Item().setUnlocalizedName("purpleDust").setCreativeTab(CreativeTabs.tabMisc).setTextureName(Main.MODID + ":purpledust");
    	//GameRegistry.registerItem(purpleDust, "purpleDust");  	
    }
}
