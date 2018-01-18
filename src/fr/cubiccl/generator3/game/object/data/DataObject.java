package fr.cubiccl.generator3.game.object.data;

import com.eclipsesource.json.JsonValue;

public abstract class DataObject implements Comparable<DataObject>
{

	public abstract String id();

	public abstract DataObject readJson(JsonValue json);

	public abstract JsonValue toJson();
	
	@Override
	public int compareTo(DataObject o)
	{
		return this.id().compareTo(o.id());
	}

}
