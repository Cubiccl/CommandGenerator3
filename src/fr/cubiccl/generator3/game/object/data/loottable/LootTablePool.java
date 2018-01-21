package fr.cubiccl.generator3.game.object.data.loottable;

import java.util.ArrayList;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import fr.cubiccl.generator3.util.JsonWritable;
import fr.cubiccl.generator3.util.rangedvalues.FloatRangedValue;
import fr.cubiccl.generator3.util.rangedvalues.IntRangedValue;

public class LootTablePool implements JsonWritable<LootTablePool>
{
	public static final String JSON_ROLLS = "rolls", JSON_BONUSROLLS = "bonus_rolls", JSON_CONDITIONS = "conditions", JSON_ENTRIES = "entries";

	private FloatRangedValue bonusRolls = new FloatRangedValue();
	private final ArrayList<LootTableCondition> conditions = new ArrayList<>();
	private final ArrayList<LootTableEntry> entries = new ArrayList<>();
	private IntRangedValue rolls = new IntRangedValue();
	public final LootTable tableParent;

	public LootTablePool(LootTable tableParent)
	{
		this.tableParent = tableParent;
	}

	public LootTable lootTableRoot()
	{
		return this.tableParent;
	}

	@Override
	public LootTablePool readJson(JsonValue json)
	{
		JsonObject root = json.asObject();

		if (root.get(JSON_CONDITIONS) != null) for (JsonValue value : root.get(JSON_CONDITIONS).asArray())
			this.conditions.add(new LootTableCondition().readJson(value));

		return this;
	}

	@Override
	public JsonValue toJson()
	{
		JsonObject root = Json.object();

		root.add(JSON_ROLLS, this.rolls.toJson());
		if (this.bonusRolls.isRanged() || this.bonusRolls.getValue() != 0) root.add(JSON_BONUSROLLS, this.bonusRolls.toJson());

		if (this.conditions.size() != 0)
		{
			JsonArray array = Json.array();
			for (LootTableCondition condition : this.conditions)
				array.add(condition.toJson());
			root.add(JSON_CONDITIONS, array);
		}

		if (this.entries.size() != 0)
		{
			JsonArray array = Json.array();
			for (LootTableEntry entry : this.entries)
				array.add(entry.toJson());
			root.add(JSON_ENTRIES, array);
		}

		return root;
	}

}
