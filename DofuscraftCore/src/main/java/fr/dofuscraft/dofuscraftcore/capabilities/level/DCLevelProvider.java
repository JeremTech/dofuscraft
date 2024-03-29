package fr.dofuscraft.dofuscraftcore.capabilities.level;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DCLevelProvider implements ICapabilitySerializable<NBTBase>
{

	@CapabilityInject(ILevel.class)
	public static final Capability<ILevel> LEVEL_CAP = null;

	private ILevel instance = LEVEL_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
	{
		return capability == LEVEL_CAP;
	}

	@Nullable
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
	{
		return capability == LEVEL_CAP ? LEVEL_CAP.cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT()
	{
		return LEVEL_CAP.getStorage().writeNBT(LEVEL_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		LEVEL_CAP.getStorage().readNBT(LEVEL_CAP, this.instance, null, nbt);
	}
}
