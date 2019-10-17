package fr.dofuscraft.dofuscraftcore.network;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCSubLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ISubLevel;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSyncSubLevel implements IMessage
{
    private int alchemist_sublevel;
    private int lumberjack_sublevel;
    private int miner_sublevel;
    private int peasant_sublevel;
    private int jeweler_sublevel;
    private int tailor_sublevel;
    private int shoemaker_sublevel;

    public PacketSyncSubLevel() {}

    public PacketSyncSubLevel(ISubLevel level)
    {
        this.alchemist_sublevel = level.getAlchemistSubLevel();
        this.lumberjack_sublevel = level.getLumberjackSubLevel();
        this.miner_sublevel = level.getMinerSubLevel();
        this.peasant_sublevel = level.getPeasantSubLevel();
        this.jeweler_sublevel = level.getJewelerSubLevel();
        this.tailor_sublevel = level.getTailorSubLevel();
        this.shoemaker_sublevel = level.getShoeMakerSubLevel();
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.alchemist_sublevel = buf.readInt();
        this.lumberjack_sublevel = buf.readInt();
        this.miner_sublevel = buf.readInt();
        this.peasant_sublevel = buf.readInt();
        this.jeweler_sublevel = buf.readInt();
        this.tailor_sublevel = buf.readInt();
        this.shoemaker_sublevel = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.alchemist_sublevel);
        buf.writeInt(this.lumberjack_sublevel);
        buf.writeInt(this.miner_sublevel);
        buf.writeInt(this.peasant_sublevel);
        buf.writeInt(this.jeweler_sublevel);
        buf.writeInt(this.tailor_sublevel);
        buf.writeInt(this.shoemaker_sublevel);
    }

    public static class Handler implements IMessageHandler<PacketSyncSubLevel, IMessage>
    {
        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PacketSyncSubLevel message, MessageContext ctx)
        {
            EntityPlayer player = Minecraft.getMinecraft().player;
            Minecraft.getMinecraft().addScheduledTask(() -> {
                if(player != null)
                {
                    ISubLevel levelCap = player.getCapability(DCSubLevelProvider. SUBLEVEL_CAP, null);
                    if(levelCap != null)
                    {
                        levelCap.setAlchemistSubLevel(message.alchemist_sublevel);
                        levelCap.setLumberjackSubLevel(message.lumberjack_sublevel);
                        levelCap.setMinerSubLevel(message.miner_sublevel);
                        levelCap.setPeasantSubLevel(message.peasant_sublevel);
                        levelCap.setJewelerSubLevel(message.jeweler_sublevel);
                        levelCap.setTailorSubLevel(message.tailor_sublevel);
                        levelCap.setShoeMakerSubLevel(message.shoemaker_sublevel);
                    }
                }
            });
            return null;
        }
    }

    public static class HandlerServer implements IMessageHandler<PacketSyncSubLevel, IMessage>
    {
        @Override
        public IMessage onMessage(PacketSyncSubLevel message, MessageContext ctx)
        {
            IThreadListener thread = Dofuscraftcore.proxy.getListener(ctx);
            EntityPlayer player = Dofuscraftcore.proxy.getPlayer(ctx);
            thread.addScheduledTask(() -> {
                if(player != null)
                {
                    ISubLevel levelCap = player.getCapability(DCSubLevelProvider. SUBLEVEL_CAP, null);
                    if(levelCap != null)
                    {
                        levelCap.setAlchemistSubLevel(message.alchemist_sublevel);
                        levelCap.setLumberjackSubLevel(message.lumberjack_sublevel);
                        levelCap.setMinerSubLevel(message.miner_sublevel);
                        levelCap.setPeasantSubLevel(message.peasant_sublevel);
                        levelCap.setJewelerSubLevel(message.jeweler_sublevel);
                        levelCap.setTailorSubLevel(message.tailor_sublevel);
                        levelCap.setShoeMakerSubLevel(message.shoemaker_sublevel);
                    }
                }
            });
            return null;
        }
    }
}
