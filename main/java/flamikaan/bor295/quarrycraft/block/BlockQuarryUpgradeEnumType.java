package flamikaan.bor295.quarrycraft.block;

import net.minecraft.util.IStringSerializable;

public enum BlockQuarryUpgradeEnumType implements IStringSerializable 
{
    SPEEDI(0, "speedi"),
    SPEEDII(1, "speedii"),
    ACCELERATION(2, "acceleration"),
    FORTUNE(3, "fortune"),
    ENERGY(4, "energy");
    

    private int ID;
    private String name;
    
    private BlockQuarryUpgradeEnumType(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return getName();
    }

    public int getID() {
        return ID;
    }
}