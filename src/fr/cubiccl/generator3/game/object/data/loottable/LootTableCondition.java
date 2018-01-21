package fr.cubiccl.generator3.game.object.data.loottable;

import java.util.HashMap;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonObject.Member;
import com.eclipsesource.json.JsonValue;

import fr.cubiccl.generator3.util.JsonWritable;
import fr.cubiccl.generator3.util.rangedvalues.IntRangedValue;
import fr.cubiccl.generator3.util.testvalues.BooleanTestValue;
import fr.cubiccl.generator3.util.testvalues.FloatTestValue;
import fr.cubiccl.generator3.util.testvalues.StringTestValue;

public class LootTableCondition implements JsonWritable<LootTableCondition>
{

	public static enum LootTableConditionType
	{
		ENTITY_PROPERTIES("entity_properties"),
		ENTITY_SCORES("entity_scores"),
		KILLED_BY_PLAYER("killed_by_player"),
		RANDOM_CHANCE("random_chance"),
		RANDOM_CHANCE_WITH_LOOTING("random_chance_with_looting");

		public static LootTableConditionType find(String id)
		{
			for (LootTableConditionType type : values())
				if (type.id.equals(id)) return type;
			return null;
		}

		public final String id;

		LootTableConditionType(String id)
		{
			this.id = id;
		}
	}

	public static final String JSON_NAME = "condition", JSON_PROPERTIES = "properties", JSON_ISONFIRE = "on_fire", JSON_SCORES = "scores",
			JSON_ENTITY = "entity", JSON_INVERSE = "inverse", JSON_CHANCE = "chance", JSON_LOOTING = "looting_multiplier";

	public final FloatTestValue chance = new FloatTestValue();
	public final BooleanTestValue isEntityOnFire = new BooleanTestValue();
	public final BooleanTestValue killerInversed = new BooleanTestValue();
	public final FloatTestValue lootingMultiplier = new FloatTestValue();
	private final HashMap<String, IntRangedValue> scores = new HashMap<>();
	public final StringTestValue testedEntity = new StringTestValue();

	private LootTableConditionType type = null;

	@Override
	public LootTableCondition readJson(JsonValue json)
	{
		JsonObject root = json.asObject();
		if (root.get(JSON_NAME) != null && (this.type = LootTableConditionType.find(root.getString(JSON_NAME, null))) != null)
		{
			if ((this.type == LootTableConditionType.ENTITY_PROPERTIES || this.type == LootTableConditionType.ENTITY_SCORES) && root.get(JSON_ENTITY) != null)
				this.testedEntity.setValue(root.getString(JSON_ENTITY, null));

			switch (this.type)
			{
				case ENTITY_PROPERTIES:
					if (root.get(JSON_PROPERTIES) != null && root.get(JSON_PROPERTIES).asObject().get(JSON_ISONFIRE) != null)
						this.isEntityOnFire.setValue(root.get(JSON_PROPERTIES).asObject().getBoolean(JSON_ISONFIRE, false));
					break;

				case ENTITY_SCORES:
					if (root.get(JSON_SCORES) != null) for (Member member : root.get(JSON_SCORES).asObject())
						this.scores.put(member.getName(), new IntRangedValue().readJson(member.getValue()));
					break;

				case KILLED_BY_PLAYER:
					if (root.get(JSON_INVERSE) != null) this.killerInversed.setValue(root.getBoolean(JSON_INVERSE, false));
					break;

				case RANDOM_CHANCE_WITH_LOOTING:
					if (root.get(JSON_LOOTING) != null) this.lootingMultiplier.setValue(root.getFloat(JSON_LOOTING, 0));

				case RANDOM_CHANCE:
					if (root.get(JSON_CHANCE) != null) this.chance.setValue(root.getFloat(JSON_CHANCE, 0));
					break;

				default:
					break;
			}

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
			if ((this.type == LootTableConditionType.ENTITY_PROPERTIES || this.type == LootTableConditionType.ENTITY_SCORES) && this.testedEntity.isSet())
				root.add(JSON_ENTITY, this.testedEntity.value());

			switch (this.type)
			{
				case ENTITY_PROPERTIES:
					if (this.isEntityOnFire.isSet()) root.add(JSON_PROPERTIES, Json.object().add(JSON_ISONFIRE, this.isEntityOnFire.value()));
					break;

				case ENTITY_SCORES:
					if (this.scores.size() != 0)
					{
						JsonObject scores = Json.object();
						for (String score : this.scores.keySet())
							scores.add(score, this.scores.get(score).toJson());
						root.add(JSON_SCORES, scores);
					}
					break;

				case KILLED_BY_PLAYER:
					if (this.killerInversed.isSet()) root.add(JSON_INVERSE, this.killerInversed.value());
					break;

				case RANDOM_CHANCE_WITH_LOOTING:
					if (this.lootingMultiplier.isSet()) root.add(JSON_LOOTING, this.lootingMultiplier.value());

				case RANDOM_CHANCE:
					if (this.chance.isSet()) root.add(JSON_CHANCE, this.chance.value());
					break;

				default:
					break;
			}

		}
		return root;
	}

}
