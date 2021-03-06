package flamikaan.bor295.quarrycraft;

import flamikaan.bor295.quarrycraft.render.BlockRenderRegister;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		
		BlockRenderRegister.preInit();
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		
		BlockRenderRegister.registerBlockRenderer();
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

}
