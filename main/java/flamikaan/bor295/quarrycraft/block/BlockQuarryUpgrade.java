package flamikaan.bor295.quarrycraft.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockQuarryUpgrade extends Block implements IMetaBlockName
{

	public static final PropertyEnum TYPE = PropertyEnum.create("type", BlockQuarryUpgradeEnumType.class);
	
	protected BlockQuarryUpgrade(String unlocalizedName, Material material, float hardness, float resistance) 
	{
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		//this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setHarvestLevel("pickaxe", 2);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, BlockQuarryUpgradeEnumType.SPEEDI));
	}
	
    public BlockQuarryUpgrade(String unlocalizedName, float hardness, float resistance) 
    {
        this(unlocalizedName, Material.rock, hardness, resistance);
    }
    
    public BlockQuarryUpgrade(String unlocalizedName) 
    {
        this(unlocalizedName, 2.0f, 10.0f);
    }
	
	@Override
	protected BlockState createBlockState() 
	{
	    return new BlockState(this, new IProperty[] { TYPE });
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		IBlockState s;
		switch (meta){
		case 0:
			s = getDefaultState().withProperty(TYPE, BlockQuarryUpgradeEnumType.SPEEDI);
			break;
		case 1:
			s = getDefaultState().withProperty(TYPE, BlockQuarryUpgradeEnumType.SPEEDII);
			break;
		case 2:
			s = getDefaultState().withProperty(TYPE, BlockQuarryUpgradeEnumType.ACCELERATION);
			break;
		case 3:
			s = getDefaultState().withProperty(TYPE, BlockQuarryUpgradeEnumType.FORTUNE);
			break;
		case 4:
			s = getDefaultState().withProperty(TYPE, BlockQuarryUpgradeEnumType.ENERGY);
			break;
		default:
			s = getDefaultState().withProperty(TYPE, BlockQuarryUpgradeEnumType.SPEEDI);
			break;
		}
	    return s;
	}

	@Override
	public int getMetaFromState(IBlockState state) 
	{
		BlockQuarryUpgradeEnumType type = (BlockQuarryUpgradeEnumType) state.getValue(TYPE);
	    return type.getID();
	}
	
	@Override
	public int damageDropped(IBlockState state) 
	{
	    return getMetaFromState(state);
	}
	
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) 
	{
	    list.add(new ItemStack(itemIn, 1, 0)); //Meta 0
	    list.add(new ItemStack(itemIn, 1, 1)); //Meta 1
	    list.add(new ItemStack(itemIn, 1, 2)); //Meta 2
	    list.add(new ItemStack(itemIn, 1, 3)); //Meta 3
	    list.add(new ItemStack(itemIn, 1, 4)); //Meta 4
	}
	
	@Override
	public String getSpecialName(ItemStack stack) 
	{
	    return "quarryUpgrade_" + stack.getItemDamage();
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos) 
	{
	    return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
	}
}
