package fr.dofuscraft.dofuscraftcore.capabilities.level;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class DCSubLevelStorage implements Capability.IStorage<ISubLevel>
{
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ISubLevel> capability, ISubLevel instance, EnumFacing side)
    {
        return new NBTTagIntArray(new int[]{instance.getAlchemistSubLevel(), instance.getLumberjackSubLevel(), instance.getMinerSubLevel(), instance.getPeasantSubLevel(), instance.getJewelerSubLevel(), instance.getTailorSubLevel(), instance.getShoeMakerSubLevel()});
    }

    @Override
    public void readNBT(Capability<ISubLevel> capability, ISubLevel instance, EnumFacing side, NBTBase nbt)
    {
        instance.setAlchemistSubLevel(((NBTTagIntArray) nbt).getIntArray()[0]);
        instance.setLumberjackSubLevel(((NBTTagIntArray) nbt).getIntArray()[1]);
        instance.setMinerSubLevel(((NBTTagIntArray) nbt).getIntArray()[2]);
        instance.setPeasantSubLevel(((NBTTagIntArray) nbt).getIntArray()[3]);


        if(((NBTTagIntArray) nbt).getIntArray().length < 7){

            instance.setJewelerSubLevel(0);
            instance.setTailorSubLevel(0);
            instance.setShoeMakerSubLevel(0);

        }else {

            instance.setJewelerSubLevel(((NBTTagIntArray) nbt).getIntArray()[4]);
            instance.setTailorSubLevel(((NBTTagIntArray) nbt).getIntArray()[5]);
            instance.setShoeMakerSubLevel(((NBTTagIntArray) nbt).getIntArray()[6]);

        }

    }
}
