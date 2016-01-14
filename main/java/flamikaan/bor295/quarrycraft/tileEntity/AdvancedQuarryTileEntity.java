package flamikaan.bor295.quarrycraft.tileEntity;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import flamikaan.bor295.quarrycraft.block.ModBlocks;
import flamikaan.bor295.quarrycraft.lib.Confighandler;

public class AdvancedQuarryTileEntity extends TileEntity implements ITickable //implements IEnergyReceiver
{
	private Configuration config = Confighandler.getConfig();
	
	private IInventory Container;
	private Random r = new Random();
	private String[] ores;
	
	private int EnergyStored = 0;
	private int EnergyMax = config.getInt("QuarryEnergyMax", "PhantomQuarry", 10000000, 10000, 2147483647, "change this value to set the Phantomquarry's energy storage capacity"); //tenmillion
	private final int EnergyUsageBase = config.getInt("QuarryEnergyUsage", "PhantomQuarry", 10000, 0, 2147483647, "change this value to set the Phantomquarry's energyusage per operation");
	private int EnergyUsageCurrent = EnergyUsageBase;
	
	private final int FortuneBase = 0;
	private int FortuneCurrent = 0;
	
	private final int SpeedBase = 1;
	private int SpeedCurrent = 1;
	
	private final int SpeedMultiplierBase = 1;
	private int SpeedMultiplierCurrent = 1;
	
	private boolean LoadChunks = false;
	
	private int CurrentX = 0;
	private int CurrentY = 0;
	private int CurrentZ = 0;
	
	private int MaxX = 40;
	private int MaxY = 1;
	private int MaxZ = 40;
	
	
	public AdvancedQuarryTileEntity()
	{
		
	}
	
	@Override
	public void update() 
	{
		if (this.worldObj.isRemote)
		{
			return;
		}
		/*
		if (LoadChunks)
		{
			Chunk chunk = this.worldObj.getChunkFromBlockCoords(this.xCoord, this.zCoord);
			Ticket t = ForgeChunkManager.requestTicket(Main.instance, this.worldObj, Type.NORMAL);
			ForgeChunkManager.forceChunk(t, new ChunkCoordIntPair(this.xCoord / 16, this.zCoord / 16));
			ForgeChunkManager.
		}
		*/
		OperateQuarry();
		
		
	}
	
	private void OperateQuarry()
	{
		if(Container != null)
		{
			int containerSize = Container.getSizeInventory();
			for (int k = 0; k < (SpeedCurrent * SpeedMultiplierCurrent); k++)
			{
				if(EnergyStored >= EnergyUsageCurrent)
				{
					EnergyStored -= EnergyUsageCurrent;
					List<ItemStack> randomMineable = getRandomMineable();
					if (randomMineable != null)
					{						
						for (int j = 0; j < randomMineable.size(); j++)
						{
							for (int i = 0; i < containerSize; i++)
							{
								
								if (Container.isItemValidForSlot(i, randomMineable.get(j)))
								{
									if(Container.getStackInSlot(i) == null)
									{
										Container.setInventorySlotContents(i, randomMineable.get(j));
										break;
									}
									else if(Container.getStackInSlot(i).isItemEqual(randomMineable.get(j)) && Container.getStackInSlot(i).stackSize < Container.getStackInSlot(i).getMaxStackSize())
									{
										Container.getStackInSlot(i).stackSize++;
										break;
									}
								}
							}
						}
					}
				}
			}
						
		}
	}
	
	private List<ItemStack> getRandomMineable()
	{	
		List<ItemStack> out = null;
		CurrentX = r.nextInt(MaxX) + this.pos.getX() - MaxX / 2;
		CurrentY = r.nextInt(this.pos.getY() - 1);
		CurrentZ = r.nextInt(MaxZ) + this.pos.getZ() - MaxZ / 2;
		
		final WorldServer w = (WorldServer)this.worldObj;
		final BlockPos pos = new BlockPos(CurrentX, CurrentY, CurrentZ);
		final IBlockState blockState = w.getBlockState(pos);
		final Block block = blockState.getBlock();
		final ItemStack blockStack = new ItemStack(block);
		final Material material = block.getMaterial();
		final float hardness = block.getBlockHardness( w, pos);
		boolean isOre = true;
		int[] oreids = OreDictionary.getOreIDs(blockStack);
		if (oreids.length == 0) isOre = false;

		if(isOre && !w.isAirBlock(pos) && hardness >= 0f)
		{
			out = blockState.getBlock().getDrops(w, pos, blockState, FortuneCurrent);
		}		
		return out;
	}
	
	public void Activate(World world, int x, int y, int z)
	{
		IInventory container;
		MaxY = y;
		
		ores = OreDictionary.getOreNames();
						
		if (world.getTileEntity(new BlockPos(x, y + 1, z)) instanceof IInventory && !world.isRemote)
		{
			this.Container = (IInventory)world.getTileEntity(new BlockPos(x, y + 1, z));
			checkEnhancements(world, x, y, z);			
		}
	}
	
	public void checkEnhancements(World world, int x, int y, int z)
	{
		EnergyUsageCurrent = EnergyUsageBase;
		FortuneCurrent = FortuneBase;
		SpeedCurrent = SpeedBase;
		SpeedMultiplierCurrent = SpeedMultiplierBase;
		LoadChunks = false;
		
		EnhanceQuarry(isBlockQuarryUpgrade(world, x - 1, y, z));
		EnhanceQuarry(isBlockQuarryUpgrade(world, x + 1, y, z));
		EnhanceQuarry(isBlockQuarryUpgrade(world, x, y, z - 1));
		EnhanceQuarry(isBlockQuarryUpgrade(world, x, y, z + 1));		
	}
	
	private void EnhanceQuarry(Integer i)
	{
		Integer upgradeint = i == null? 6 : i;
		switch (upgradeint)
		{
			case 0: 
				SpeedCurrent += 1;
				break;
			case 1:
				SpeedCurrent += 3;
				break;
			case 2:
				SpeedMultiplierCurrent += 1;
				break;
			case 3:
				FortuneCurrent += 1;
				break;
			case 4:
				LoadChunks = true;
				System.out.println("This does not yet work");
				break;
			case 5:
				EnergyUsageCurrent -= 1500;
				break;
			default:
				break;
		}
	}
	
	private Integer isBlockQuarryUpgrade(World world, int x, int y, int z)
	{
		ItemStack blockStack = new ItemStack(world.getBlockState(new BlockPos(x, y, z)).getBlock(),1 , world.getBlockState(new BlockPos(x, y, z)).getBlock().getMetaFromState(world.getBlockState(new BlockPos(x, y, z))));
		for(int i = 0; i < 6; i++)
		{
			ItemStack blockStack2 = new ItemStack(ModBlocks.advancedQuarryUpgrade,1 ,i );
			if (blockStack.isItemEqual(blockStack2))
			{
				return i;
			}
		}		
		return null;		
	}
	
	public void setContainer(IInventory container)
	{
		this.Container = container;
	}
	
	//energy
	/*
	@Override
	public boolean canConnectEnergy(ForgeDirection direction) 
	{
		return true;
	}
	@Override
	public int getEnergyStored(ForgeDirection direction) 
	{
		return EnergyStored;
	}
	@Override
	public int getMaxEnergyStored(ForgeDirection direction) 
	{
		return EnergyMax;
	}
	@Override
	public int receiveEnergy(ForgeDirection direction, int maxAmount, boolean simulated) 
	{
		if (!simulated)
		{
			if (EnergyStored <= EnergyMax - (EnergyUsageCurrent * SpeedCurrent * SpeedMultiplierCurrent))
			{
				EnergyStored += (EnergyUsageCurrent * SpeedCurrent * SpeedMultiplierCurrent);
				return (EnergyUsageCurrent * SpeedCurrent * SpeedMultiplierCurrent);
			}
			if (EnergyStored < EnergyMax)
			{
				int i = EnergyMax - EnergyStored;
				EnergyStored += i;
				return i;
			}
		}
		return 0;	
	}*/
			
}
