package fr.cubiccl.generator3.game.object.data;

import com.eclipsesource.json.JsonValue;

import fr.cubiccl.generator3.game.datapack.DataPack;
import fr.cubiccl.generator3.game.datapack.DataPack.VanillaDataPack;
import fr.cubiccl.generator3.game.datapack.DataPacks;

public abstract class DataObject implements Comparable<DataObject>
{

	/** The ID of the Data Pack this Object is from. */
	private int datapack = -1;

	@Override
	public int compareTo(DataObject o)
	{
		return this.id().compareTo(o.id());
	}

	public DataPack getDatapack()
	{
		return DataPacks.get(this.datapack);
	}

	public VanillaDataPack getVanillaPack()
	{
		return this.getDatapack().vanillaPack();
	}

	public abstract String id();

	public abstract DataObject readJson(JsonValue json);

	public void setDatapack(int datapack)
	{
		this.datapack = datapack;
	}

	public abstract JsonValue toJson();

}
