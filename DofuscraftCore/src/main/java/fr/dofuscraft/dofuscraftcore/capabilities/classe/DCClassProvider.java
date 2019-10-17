package fr.dofuscraft.dofuscraftcore.capabilities.classe;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DCClassProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IClass.class)
    public static final Capability<IClass> CLASS_CAP = null;

    private IClass instance = CLASS_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CLASS_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        return capability == CLASS_CAP ? CLASS_CAP.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return CLASS_CAP.getStorage().writeNBT(CLASS_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        CLASS_CAP.getStorage().readNBT(CLASS_CAP, this.instance, null, nbt);
    }
}
