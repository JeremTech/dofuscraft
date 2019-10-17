package fr.dofuscraft.dofuscraftcore.capabilities.level;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DCSubLevelProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(ISubLevel.class)
    public static final Capability<ISubLevel> SUBLEVEL_CAP = null;

    private ISubLevel instance = SUBLEVEL_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == SUBLEVEL_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        return capability == SUBLEVEL_CAP ? SUBLEVEL_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return SUBLEVEL_CAP.getStorage().writeNBT(SUBLEVEL_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        SUBLEVEL_CAP.getStorage().readNBT(SUBLEVEL_CAP, this.instance, null, nbt);
    }
}
