package fr.dofuscraft.dofuscraftcore.capabilities.classe;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.network.PacketDCClass;
import net.minecraft.entity.player.EntityPlayerMP;

public class DCClass implements IClass
{
    private String name = "";
    private String id = "";
    private String classItem = "";

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public void setClassItem(String item)
    {
        this.classItem = item;
    }

    @Override
    public String getClassItem()
    {
        return this.classItem;
    }

    @Override
    public void setID(String id)
    {
        this.id = id;
    }

    @Override
    public String getID()
    {
        return this.id;
    }

    @Override
    public void sync(EntityPlayerMP player)
    {
        PacketDCClass packet = new PacketDCClass(player.getCapability(DCClassProvider.CLASS_CAP, null));

        if(!player.getEntityWorld().isRemote)
        {
            Dofuscraftcore.network.sendTo(packet, player);
        }
        else
        {
            Dofuscraftcore.network.sendToServer(packet);
        }
    }
}
