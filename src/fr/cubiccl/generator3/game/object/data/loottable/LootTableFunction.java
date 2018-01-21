package fr.cubiccl.generator3.game.object.data.loottable;

import java.util.ArrayList;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import fr.cubiccl.generator3.game.object.type.Enchantment;
import fr.cubiccl.generator3.util.JsonWritable;
import fr.cubiccl.generator3.util.rangedvalues.FloatRangedValue;
import fr.cubiccl.generator3.util.rangedvalues.IntRangedValue;
import fr.cubiccl.generator3.util.testvalues.BooleanTestValue;
import fr.cubiccl.generator3.util.testvalues.IntTestValue;
import fr.cubiccl.generator3.util.testvalues.StringTestValue;

public class LootTableFunction implements JsonWritable<LootTableFunction>
{

	public static enum LootTableFunctionType
	{
		ENCHANT_RANDOMLY("enchant_randomly"),
		ENCHANT_WITH_LEVELS("enchant_with_levels"),
		FURNACE_SMELT("furnace_smelt"),
		LOOTING_ENCHANT("looting_enchant"),
		SET_ATTRIBUTES("set_attributes"),
		SET_COUNT("set_count"),
		SET_DAMAGE("set_damage"),
		SET_NBT("set_nbt");

		public static LootTableFunctionType find(String id)
		{
			for (LootTableFunctionType type : values())
				if (type.id.equals(id)) return type;
			return null;
		}

		public final String id;

		LootTableFunctionType(String id)
		{
			this.id = id;
		}
	}

	public static final String JSON_NAME = "function", JSON_CONDITIONS = "conditions", JSON_ENCHANTMENTS = "enchantments", JSON_TREASURE = "treasure",
			JSON_LEVELS = "levels", JSON_COUNT = "count", JSON_LIMIT = "limit", JSON_MODIFIERS = "modifiers", JSON_DAMAGE = "damage", JSON_NBT = "tag";

	private ArrayList<LootTableCondition> conditions = new ArrayList<>();
	public final IntRangedValue count = new IntRangedValue();
	public final FloatRangedValue damage = new FloatRangedValue();
	private final ArrayList<Enchantment> enchantments = new ArrayList<>();
	public final LootTableEntry entryParent;
	public final IntRangedValue levels = new IntRangedValue();
	public final IntTestValue limit = new IntTestValue();
	public final StringTestValue nbt = new StringTestValue();

	public final BooleanTestValue treasure = new BooleanTestValue();
	private LootTableFunctionType type = null;

	public LootTableFunction(LootTableEntry entryParent)
	{
		this.entryParent = entryParent;
	}

	public LootTable lootTableRoot()
	{
		return this.entryParent.lootTableRoot();
	}

	@Override
	public LootTableFunction readJson(JsonValue json)
	{
		JsonObject root = Json.object();
		if (root.get(JSON_NAME) != null && (this.type = LootTableFunctionType.find(root.getString(JSON_NAME, null))) != null)
		{
			if (root.get(JSON_ENCHANTMENTS) != null)
			{
				for (JsonValue value : root.get(JSON_ENCHANTMENTS).asArray())
					this.enchantments.add(this.lootTableRoot().getVanillaPack().enchantments.find(value.asString()));
			}

			if (root.get(JSON_LEVELS) != null) this.levels.readJson(root.get(JSON_LEVELS));
			if (root.get(JSON_TREASURE) != null) this.treasure.setValue(root.getBoolean(JSON_TREASURE, false));

			if (root.get(JSON_LIMIT) != null) this.limit.setValue(root.getInt(JSON_LIMIT, 0));

			if (root.get(JSON_COUNT) != null) this.count.readJson(root.get(JSON_COUNT));
			if (root.get(JSON_DAMAGE) != null) this.damage.readJson(root.get(JSON_DAMAGE));

			if (root.get(JSON_NBT) != null) this.nbt.setValue(root.getString(JSON_NBT, null));

			if (root.get(JSON_CONDITIONS) != null) for (JsonValue value : root.get(JSON_CONDITIONS).asArray())
				this.conditions.add(new LootTableCondition().readJson(value));

		}
		return this;
	}

	@Override
	public JsonValue toJson()
	{
		JsonObject root = Json.object();
		if (this.type != null)
		{
			root.add(JSON_NAME, this.type.id);

			switch (this.type)
			{
				case ENCHANT_RANDOMLY:
					if (this.enchantments.size() != 0)
					{
						JsonArray array = Json.array();
						for (Enchantment enchantment : this.enchantments)
							array.add(enchantment.id());
						root.add(JSON_ENCHANTMENTS, array);
					}
					break;

				case ENCHANT_WITH_LEVELS:
					root.add(JSON_LEVELS, this.levels.toJson());
					if (this.treasure.isSet()) root.add(JSON_TREASURE, this.treasure.value());
					break;

				case LOOTING_ENCHANT:
					if (this.limit.isSet()) root.add(JSON_LIMIT, this.limit.value());

				case SET_COUNT:
					root.add(JSON_COUNT, this.count.toJson());
					break;

				case SET_DAMAGE:
					root.add(JSON_DAMAGE, this.damage.toJson());
					break;

				case SET_NBT:
					if (this.nbt.isSet()) root.add(JSON_NBT, this.nbt.value());
					break;

				default:
					break;
			}

			if (this.conditions.size() != 0)
			{
				JsonArray array = Json.array();
				for (LootTableCondition condition : this.conditions)
					array.add(condition.toJson());
				root.add(JSON_CONDITIONS, array);
			}

		}
		return root;
	}

}
