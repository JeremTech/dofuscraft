package fr.dofuscraft.dofuscraftcore.capabilities.level;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class DCLevelStorage implements Capability.IStorage<ILevel>
{
	@Nullable
	@Override
	public NBTBase writeNBT(Capability<ILevel> capability, ILevel instance, EnumFacing side)
	{
		return new NBTTagIntArray(new int[]{instance.getAlchemistLevel(), instance.getLumberjackLevel(), instance.getMinerLevel(), instance.getPeasantLevel(), instance.getJewelerLevel(), instance.getTailorLevel(), instance.getShoeMakerLevel()});
	}

	@Override
	public void readNBT(Capability<ILevel> capability, ILevel instance, EnumFacing side, NBTBase nbt)
	{
		instance.setAlchemistLevel(((NBTTagIntArray) nbt).getIntArray()[0]);
		instance.setLumberjackLevel(((NBTTagIntArray) nbt).getIntArray()[1]);
		instance.setMinerLevel(((NBTTagIntArray) nbt).getIntArray()[2]);
		instance.setPeasantLevel(((NBTTagIntArray) nbt).getIntArray()[3]);

		if(((NBTTagIntArray) nbt).getIntArray().length < 7){

			instance.setJewelerLevel(1);
			instance.setTailorLevel(1);
			instance.setShoeMakerLevel(1);

		}else {

			instance.setJewelerLevel(((NBTTagIntArray) nbt).getIntArray()[4]);
			instance.setTailorLevel(((NBTTagIntArray) nbt).getIntArray()[5]);
			instance.setShoeMakerLevel(((NBTTagIntArray) nbt).getIntArray()[6]);

		}

	}
}
