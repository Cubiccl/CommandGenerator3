package fr.cubiccl.generator3.game.object.data;

import com.eclipsesource.json.JsonValue;

import fr.cubiccl.generator3.game.object.GameObject;

public abstract class DataObject extends GameObject implements Comparable<DataObject>
{

	@Override
	public int compareTo(DataObject o)
	{
		return this.id().compareTo(o.id());
	}

	public abstract String id();

	public abstract DataObject readJson(JsonValue json);

	public abstract JsonValue toJson();

}
