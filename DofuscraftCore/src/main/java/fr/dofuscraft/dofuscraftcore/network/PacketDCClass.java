package fr.dofuscraft.dofuscraftcore.network;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.capabilities.classe.DCClassProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.classe.IClass;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketDCClass implements IMessage
{
    private String id;
    private String name;
    private String classItem;

    public PacketDCClass() {}

    public PacketDCClass(IClass classe)
    {
        this.id = classe.getID();
        this.name = classe.getName();
        this.classItem = classe.getClassItem();
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.id = ByteBufUtils.readUTF8String(buf);
        this.name = ByteBufUtils.readUTF8String(buf);
        this.classItem = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, this.id);
        ByteBufUtils.writeUTF8String(buf, this.name);
        ByteBufUtils.writeUTF8String(buf, this.classItem);
    }

    public static class Handler implements IMessageHandler<PacketDCClass, IMessage>
    {
        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PacketDCClass message, MessageContext ctx)
        {
            EntityPlayer player = Minecraft.getMinecraft().player;
            Minecraft.getMinecraft().addScheduledTask(() -> {
                if(player != null)
                {
                    IClass class_cap = player.getCapability(DCClassProvider.CLASS_CAP, null);
                    if(class_cap != null)
                    {
                        class_cap.setID(message.id);
                        class_cap.setName(message.name);
                        class_cap.setClassItem(message.classItem);
                    }
                }
            });

            return null;
        }
    }

    public static class HandlerServer implements IMessageHandler<PacketDCClass, IMessage>
    {

        @Override
        public IMessage onMessage(PacketDCClass message, MessageContext ctx)
        {
            EntityPlayer player = Dofuscraftcore.proxy.getPlayer(ctx);
            IThreadListener thread = Dofuscraftcore.proxy.getListener(ctx);
            thread.addScheduledTask(() -> {
                if(player != null)
                {
                    IClass class_cap = player.getCapability(DCClassProvider.CLASS_CAP, null);
                    if(class_cap != null)
                    {
                        class_cap.setID(message.id);
                        class_cap.setName(message.name);
                        class_cap.setClassItem(message.classItem);
                    }
                }
            });


            return null;
        }
    }
}
