package fr.dofuscraft.dofuscraftcore.capabilities.level;

import net.minecraft.entity.player.EntityPlayerMP;

public interface ILevel
{

	void setAlchemistLevel(int level);
	int getAlchemistLevel();

	void setLumberjackLevel(int level);
	int getLumberjackLevel();

	void setMinerLevel(int level);
	int getMinerLevel();

	void setPeasantLevel(int level);
	int getPeasantLevel();

	void setJewelerLevel(int level);
	int getJewelerLevel();

	void setTailorLevel(int level);
	int getTailorLevel();

	void setShoeMakerLevel(int level);
	int getShoeMakerLevel();

	void sync(EntityPlayerMP player);

}
