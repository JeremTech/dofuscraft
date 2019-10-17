package fr.dofuscraft.dofuscraftcore.capabilities.classe;

import net.minecraft.entity.player.EntityPlayerMP;

public interface IClass
{
    void setName(String name);
    String getName();

    void setClassItem(String item);
    String getClassItem();

    void setID(String id);
    String getID();

    void sync(EntityPlayerMP player);
}
