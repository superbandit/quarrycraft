package flamikaan.bor295.quarrycraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import flamikaan.bor295.quarrycraft.tileEntity.AdvancedQuarryTileEntity;

public class AdvancedQuarry extends Block implements ITileEntityProvider
{
	//public IIcon[] icons = new IIcon[2];
	
	protected AdvancedQuarry(String unlocalizedName, Material material, float hardness, float resistance) 
	{
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setHarvestLevel("pickaxe", 3);
        this.setStepSound(soundTypeStone);
        this.isBlockContainer = true;
	}
	
    public AdvancedQuarry(String unlocalizedName, float hardness, float resistance) 
    {
        this(unlocalizedName, Material.rock, hardness, resistance);
    }
    
    public AdvancedQuarry(String unlocalizedName) 
    {
        this(unlocalizedName, 2.0f, 10.0f);
    }
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new AdvancedQuarryTileEntity();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		AdvancedQuarryTileEntity entity = (AdvancedQuarryTileEntity)world.getTileEntity(pos);
		entity.Activate(world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) 
	{
        super.breakBlock(world, pos, state);
        world.removeTileEntity(pos);
    }
	
    @Override
    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
    }	
}
