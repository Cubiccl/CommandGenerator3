package fr.cubiccl.generator3.game.object.data.loottable;

import java.util.ArrayList;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import fr.cubiccl.generator3.game.object.type.Item;
import fr.cubiccl.generator3.util.JsonWritable;

public class LootTableEntry implements JsonWritable<LootTableEntry>
{
	public static enum LootTableEntryType
	{
		EMPTY("empty"),
		ITEM("item"),
		LOOT_TABLE("loot_table");

		public static LootTableEntryType find(String id)
		{
			for (LootTableEntryType type : values())
				if (type.id.equals(id)) return type;
			return EMPTY;
		}

		public final String id;

		LootTableEntryType(String id)
		{
			this.id = id;
		}
	}

	public static final String JSON_TYPE = "type", JSON_NAME = "name", JSON_WEIGHT = "weight", JSON_QUALITY = "quality", JSON_CONDITIONS = "conditions",
			JSON_FUNCTIONS = "functions";

	private final ArrayList<LootTableCondition> conditions = new ArrayList<>();
	private final ArrayList<LootTableFunction> functions = new ArrayList<>();
	/** If type is ITEM, the Item to generate. */
	private Item item;
	/** If type is LOOT_TABLE, the Loot Table to use. */
	private LootTable lootTable;
	public final LootTablePool poolParent;
	private int quality = 0;
	private LootTableEntryType type = LootTableEntryType.EMPTY;
	private int weight = 1;

	public LootTableEntry(LootTablePool poolParent)
	{
		this.poolParent = poolParent;
	}

	public LootTable lootTableRoot()
	{
		return this.poolParent.lootTableRoot();
	}

	@Override
	public LootTableEntry readJson(JsonValue json)
	{
		JsonObject root = Json.object();
		this.type = LootTableEntryType.find(root.getString(JSON_TYPE, null));
		if (this.type == LootTableEntryType.ITEM) this.item = this.lootTableRoot().getVanillaPack().items.find(root.getString(JSON_NAME, null));
		else if (this.type == LootTableEntryType.LOOT_TABLE)
			this.lootTable = this.lootTableRoot().getVanillaPack().lootTables.find(root.getString(JSON_NAME, null));
		this.weight = root.getInt(JSON_WEIGHT, 1);
		this.quality = root.getInt(JSON_QUALITY, 0);

		if (root.get(JSON_CONDITIONS) != null) for (JsonValue value : root.get(JSON_CONDITIONS).asArray())
			this.conditions.add(new LootTableCondition().readJson(value));

		if (root.get(JSON_FUNCTIONS) != null) for (JsonValue value : root.get(JSON_FUNCTIONS).asArray())
			this.functions.add(new LootTableFunction(this).readJson(value));

		return this;
	}

	@Override
	public JsonValue toJson()
	{
		JsonObject root = Json.object();
		root.add(JSON_TYPE, this.type.id);
		if (this.type == LootTableEntryType.ITEM) root.add(JSON_NAME, this.item.id());
		else if (this.type == LootTableEntryType.LOOT_TABLE) root.add(JSON_NAME, this.lootTable.id());
		root.add(JSON_WEIGHT, this.weight);
		root.add(JSON_QUALITY, this.quality);

		if (this.conditions.size() != 0)
		{
			JsonArray array = Json.array();
			for (LootTableCondition condition : this.conditions)
				array.add(condition.toJson());
			root.add(JSON_CONDITIONS, array);
		}

		if (this.functions.size() != 0)
		{
			JsonArray array = Json.array();
			for (LootTableFunction function : this.functions)
				array.add(function.toJson());
			root.add(JSON_FUNCTIONS, array);
		}

		return root;
	}

}
