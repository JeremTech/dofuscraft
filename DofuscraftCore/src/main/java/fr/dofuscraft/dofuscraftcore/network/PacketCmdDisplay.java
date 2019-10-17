package fr.dofuscraft.dofuscraftcore.network;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketCmdDisplay implements IMessage {

    public int guiID;

    public PacketCmdDisplay() {}

    public PacketCmdDisplay(int guiID)
    {
        this.guiID = guiID;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.guiID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(guiID);
    }


    public static class Handler implements IMessageHandler<PacketCmdDisplay, IMessage>
    {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PacketCmdDisplay message, MessageContext ctx)
        {
            IThreadListener thread = FMLCommonHandler.instance().getWorldThread(ctx.netHandler);
            EntityPlayer player = Minecraft.getMinecraft().player;

            Minecraft.getMinecraft().addScheduledTask(() -> {

                if(message.guiID > 1)
                {
                    player.openGui(Dofuscraftcore.instance, message.guiID, player.world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());

                }
            });

            return null;

        }
    }

}
