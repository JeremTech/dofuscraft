package fr.dofuscraft.dofuscraftcore.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketCommand implements IMessage
{
    public String command;

    public PacketCommand() {}

    public PacketCommand(String command)
    {
        this.command = command;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.command = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, this.command);
    }

    public static class HandlerServer implements IMessageHandler<PacketCommand, IMessage>
    {
        @Override
        public IMessage onMessage(PacketCommand message, MessageContext ctx)
        {
            IThreadListener thread = FMLCommonHandler.instance().getWorldThread(ctx.netHandler);
            EntityPlayer player = ctx.getServerHandler().player;

            thread.addScheduledTask(() -> {

                player.world.getMinecraftServer().getCommandManager().executeCommand(player.world.getMinecraftServer(), message.command);

            });

            return null;

        }
    }
}
