package flamikaan.bor295.quarrycraft.lib;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Confighandler 
{
	
	private static Configuration config;
	
	public static void Init(FMLPreInitializationEvent e)
	{
    	config = new Configuration(e.getSuggestedConfigurationFile());
    	config.load();
    	config.getInt("QuarryEnergyUsage", "PhantomQuarry", 10000, 0, 2147483647, "change this value to set the Phantomquarry's energyusage per operation");
    	config.getInt("QuarryEnergyMax", "PhantomQuarry", 10000000, 10000, 2147483647, "change this value to set the Phantomquarry's energy storage capacity");
    	config.save();
	}
	
	public static Configuration getConfig()
	{
		return config;
	}
}
