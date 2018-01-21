package fr.cubiccl.generator3.game.object.data.loottable;

import java.util.ArrayList;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import fr.cubiccl.generator3.game.object.data.DataObject;

public class LootTable extends DataObject
{
	public static final String JSON_POOLS = "pools";

	private String name;
	private ArrayList<LootTablePool> pools = new ArrayList<>();

	public LootTable(String name, int datapack)
	{
		this.name = name;
		this.setDatapack(datapack);
	}

	@Override
	public String idWithoutNamespace()
	{
		return this.name;
	}

	@Override
	public LootTable readJson(JsonValue json)
	{
		JsonObject root = json.asObject();
		if (root.get(JSON_POOLS) != null) for (JsonValue value : root.get(JSON_POOLS).asArray())
			this.pools.add(new LootTablePool(this).readJson(value));
		return this;
	}

	@Override
	public JsonValue toJson()
	{
		JsonArray array = Json.array();
		for (LootTablePool pool : this.pools)
			array.add(pool.toJson());
		return Json.object().add(JSON_POOLS, array);
	}

}
