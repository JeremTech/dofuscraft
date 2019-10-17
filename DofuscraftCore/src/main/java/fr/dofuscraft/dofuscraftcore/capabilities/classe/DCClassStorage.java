package fr.dofuscraft.dofuscraftcore.capabilities.classe;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class DCClassStorage implements Capability.IStorage<IClass>
{
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IClass> capability, IClass instance, EnumFacing side)
    {
        NBTTagList tagList = new NBTTagList();

        tagList.appendTag(new NBTTagString(instance.getID()));
        tagList.appendTag(new NBTTagString(instance.getName()));
        tagList.appendTag(new NBTTagString(instance.getClassItem()));

        return tagList;
    }

    @Override
    public void readNBT(Capability<IClass> capability, IClass instance, EnumFacing side, NBTBase nbt)
    {
        instance.setID(((NBTTagList) nbt).getStringTagAt(0));
        instance.setName(((NBTTagList) nbt).getStringTagAt(1));
        instance.setClassItem(((NBTTagList) nbt).getStringTagAt(2));
    }
}
