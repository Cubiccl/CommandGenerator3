package fr.cubiccl.generator3.game.object.data;

import com.eclipsesource.json.JsonObject.Member;

public abstract class DataObject implements Comparable<DataObject>
{

	public abstract String id();

	public abstract DataObject readJson(Member json);

	public abstract Member toJson();
	
	@Override
	public int compareTo(DataObject o)
	{
		return this.id().compareTo(o.id());
	}

}
