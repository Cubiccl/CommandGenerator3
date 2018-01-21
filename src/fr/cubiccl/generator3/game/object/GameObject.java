package fr.cubiccl.generator3.game.object;

import fr.cubiccl.generator3.game.datapack.DataPack;
import fr.cubiccl.generator3.game.datapack.DataPack.VanillaDataPack;
import fr.cubiccl.generator3.game.datapack.DataPacks;

public class GameObject
{

	/** The ID of the Data Pack this Object is from. */
	private int datapack = -1;

	public DataPack getDatapack()
	{
		return DataPacks.get(this.datapack);
	}

	public VanillaDataPack getVanillaPack()
	{
		return this.getDatapack().vanillaPack();
	}

	public void setDatapack(int datapack)
	{
		this.datapack = datapack;
	}

}
