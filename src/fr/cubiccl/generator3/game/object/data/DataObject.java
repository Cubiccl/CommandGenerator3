package fr.cubiccl.generator3.game.object.data;

import com.eclipsesource.json.JsonValue;

import fr.cubiccl.generator3.game.object.GameObject;

public abstract class DataObject extends GameObject implements Comparable<DataObject>
{

	@Override
	public int compareTo(DataObject o)
	{
		return this.idWithoutNamespace().compareTo(o.idWithoutNamespace());
	}

	public String id()
	{
		return this.getDatapack().namespace() + this.idWithoutNamespace();
	}

	public abstract String idWithoutNamespace();

	public abstract DataObject readJson(JsonValue json);

	public abstract JsonValue toJson();

}
