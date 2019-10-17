package fr.dofuscraft.dofuscraftcore.network;

import fr.dofuscraft.dofuscraftcore.Dofuscraftcore;
import fr.dofuscraft.dofuscraftcore.capabilities.level.DCLevelProvider;
import fr.dofuscraft.dofuscraftcore.capabilities.level.ILevel;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSyncLevel implements IMessage
{
	private int alchemist_level;
	private int lumberjack_level;
	private int miner_level;
	private int peasant_level;
	private int jeweler_level;
	private int tailor_level;
	private int shoemaker_level;

	public PacketSyncLevel() {}

	public PacketSyncLevel(ILevel level)
	{
		this.alchemist_level = level.getAlchemistLevel();
		this.lumberjack_level = level.getLumberjackLevel();
		this.miner_level = level.getMinerLevel();
		this.peasant_level = level.getPeasantLevel();
		this.jeweler_level = level.getJewelerLevel();
		this.tailor_level = level.getTailorLevel();
		this.shoemaker_level = level.getShoeMakerLevel();
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.alchemist_level = buf.readInt();
		this.lumberjack_level = buf.readInt();
		this.miner_level = buf.readInt();
		this.peasant_level = buf.readInt();
		this.jeweler_level = buf.readInt();
		this.tailor_level = buf.readInt();
		this.shoemaker_level = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.alchemist_level);
		buf.writeInt(this.lumberjack_level);
		buf.writeInt(this.miner_level);
		buf.writeInt(this.peasant_level);
		buf.writeInt(this.jeweler_level);
		buf.writeInt(this.tailor_level);
		buf.writeInt(this.shoemaker_level);
	}

	public static class Handler implements IMessageHandler<PacketSyncLevel, IMessage>
	{
	    @SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(PacketSyncLevel message, MessageContext ctx)
		{
			EntityPlayer player = Minecraft.getMinecraft().player;
			Minecraft.getMinecraft().addScheduledTask(() -> {
				if(player != null)
				{
					ILevel levelCap = player.getCapability(DCLevelProvider.LEVEL_CAP, null);
					if(levelCap != null)
					{
						levelCap.setAlchemistLevel(message.alchemist_level);
						levelCap.setLumberjackLevel(message.lumberjack_level);
						levelCap.setMinerLevel(message.miner_level);
						levelCap.setPeasantLevel(message.peasant_level);
						levelCap.setJewelerLevel(message.jeweler_level);
						levelCap.setTailorLevel(message.tailor_level);
						levelCap.setShoeMakerLevel(message.shoemaker_level);
					}
				}
			});
			return null;
		}
	}

    public static class HandlerServer implements IMessageHandler<PacketSyncLevel, IMessage>
    {
        @Override
        public IMessage onMessage(PacketSyncLevel message, MessageContext ctx)
        {
            IThreadListener thread = Dofuscraftcore.proxy.getListener(ctx);
            EntityPlayer player = Dofuscraftcore.proxy.getPlayer(ctx);
            thread.addScheduledTask(() -> {
                if(player != null)
                {
                    ILevel levelCap = player.getCapability(DCLevelProvider.LEVEL_CAP, null);
                    if(levelCap != null)
                    {
                        levelCap.setAlchemistLevel(message.alchemist_level);
                        levelCap.setLumberjackLevel(message.lumberjack_level);
                        levelCap.setMinerLevel(message.miner_level);
                        levelCap.setPeasantLevel(message.peasant_level);
						levelCap.setJewelerLevel(message.jeweler_level);
						levelCap.setTailorLevel(message.tailor_level);
						levelCap.setShoeMakerLevel(message.shoemaker_level);
                    }
                }
            });
            return null;
        }
    }
}
