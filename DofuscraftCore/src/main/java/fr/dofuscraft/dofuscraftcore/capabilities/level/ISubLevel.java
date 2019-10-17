package fr.dofuscraft.dofuscraftcore.capabilities.level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public interface ISubLevel
{
    void setAlchemistSubLevel(int sublevel);
    int getAlchemistSubLevel();

    void setLumberjackSubLevel(int sublevel);
    int getLumberjackSubLevel();

    void setMinerSubLevel(int sublevel);
    int getMinerSubLevel();

    void setPeasantSubLevel(int sublevel);
    int getPeasantSubLevel();

    void setJewelerSubLevel(int sublevel);
    int getJewelerSubLevel();

    void setTailorSubLevel(int sublevel);
    int getTailorSubLevel();

    void setShoeMakerSubLevel(int sublevel);
    int getShoeMakerSubLevel();

    void sync(EntityPlayerMP player);

    void checkLevel(EntityPlayerMP player, String job);
    int getSubLevelRequire(int level);

}
