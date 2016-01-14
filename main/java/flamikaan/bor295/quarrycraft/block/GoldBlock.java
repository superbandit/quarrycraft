package flamikaan.bor295.quarrycraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import flamikaan.bor295.quarrycraft.Main;

public class GoldBlock extends Block
{
	protected GoldBlock(String unlocalizedName, Material material, float hardness, float resistance) 
	{
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setLightLevel(1.0F);
        this.setHarvestLevel("pickaxe", 3);
        this.setStepSound(soundTypeStone);
	}
	
    public GoldBlock(String unlocalizedName, float hardness, float resistance) 
    {
        this(unlocalizedName, Material.rock, hardness, resistance);
    }
    
    public GoldBlock(String unlocalizedName) 
    {
        this(unlocalizedName, 2.0f, 10.0f);
    }
}
