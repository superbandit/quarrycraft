package flamikaan.bor295.quarrycraft;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import flamikaan.bor295.quarrycraft.block.ModBlocks;
import flamikaan.bor295.quarrycraft.item.ModItems;
import flamikaan.bor295.quarrycraft.lib.Confighandler;

public class CommonProxy 
{
    public void preInit(FMLPreInitializationEvent e) 
    {
    	Confighandler.Init(e);
    	ModItems.init();
    	ModBlocks.init();
    }

    public void init(FMLInitializationEvent e) {

    }

    public void postInit(FMLPostInitializationEvent e) 
    {
    	ModBlocks.postInit();
    }
}
