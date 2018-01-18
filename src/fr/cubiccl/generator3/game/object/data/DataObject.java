package fr.cubiccl.generator3.game.object.data;

import com.eclipsesource.json.JsonObject.Member;

public abstract class DataObject
{

	public abstract DataObject readJson(Member json);

	public abstract Member toJson();

}
