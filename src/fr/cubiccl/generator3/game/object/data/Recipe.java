package fr.cubiccl.generator3.game.object.data;

import java.util.ArrayList;
import java.util.HashMap;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonObject.Member;
import com.eclipsesource.json.JsonValue;

import fr.cubiccl.generator3.game.object.instance.ItemStack;
import fr.cubiccl.generator3.game.object.type.Item;
import fr.cubiccl.generator3.util.Logger;

public class Recipe extends DataObject
{
	public static class RecipeIngredient
	{
		private RecipeIngredientType type;
		private ArrayList<String> values;

		public RecipeIngredient()
		{
			this(RecipeIngredientType.ITEM, null);
		}

		public RecipeIngredient(RecipeIngredientType type, String value)
		{
			super();
			this.type = type;
			this.values = new ArrayList<>();
			if (value != null) this.values.add(value);
		}

		public RecipeIngredient readJson(JsonValue json)
		{
			if (json.isObject())
			{
				JsonObject root = json.asObject();
				if (root.get(JSON_ITEM) != null)
				{
					this.type = RecipeIngredientType.ITEM;
					this.values.clear();
					this.values.add(root.getString(JSON_ITEM, null));
				} else if (root.get(JSON_TAG) != null)
				{
					this.type = RecipeIngredientType.TAG;
					this.values.clear();
					this.values.add(root.getString(JSON_TAG, null));
				}
			} else if (json.isArray())
			{
				this.type = RecipeIngredientType.ITEM;
				for (JsonValue value : json.asArray())
					this.values.add(value.asObject().getString(JSON_ITEM, null));
			}
			return this;
		}

		public JsonValue toJson()
		{
			if (this.values.size() == 1) return Json.object().add(this.type == RecipeIngredientType.ITEM ? JSON_ITEM : JSON_TAG, this.values.get(0));
			JsonArray json = Json.array();
			for (String value : this.values)
				json.add(Json.object().add(JSON_ITEM, value));
			return json;
		}
	}

	public static enum RecipeIngredientType
	{
		ITEM,
		ITEMS,
		TAG;
	}

	public static enum RecipeType
	{
		SHAPED("crafting_shaped"),
		SHAPELESS("crafting_shapeless"),
		SPECIAL_ARMORDYE("crafting_special_armordye"),
		SPECIAL_BANNERADDPATTERN("crafting_special_banneraddpattern"),
		SPECIAL_BANNERDUPLICATE("crafting_special_bannerduplicate"),
		SPECIAL_BOOKCLONING("crafting_special_bookcloning"),
		SPECIAL_FIREWORK_ROCKET("crafting_special_firework_rocket"),
		SPECIAL_FIREWORK_STAR("crafting_special_firework_star"),
		SPECIAL_FIREWORK_STAR_FADE("crafting_special_firework_star_fade"),
		SPECIAL_MAPCLONING("crafting_special_mapcloning"),
		SPECIAL_MAPEXTENDING("crafting_special_mapextending"),
		SPECIAL_REPAIRITEM("crafting_special_repairitem"),
		SPECIAL_SHIELDDECORATION("crafting_special_shielddecoration"),
		SPECIAL_SHULKERBOXCOLORING("crafting_special_shulkerboxcoloring"),
		SPECIAL_TIPPEDARROW("crafting_special_tippedarrow");

		public static RecipeType find(String id)
		{
			for (RecipeType recipe : values())
				if (recipe.id.equals(id)) return recipe;
			Logger.log("Recipe type " + id + " is unknown! Returning default \"" + SHAPED.id + "\".");
			return null;
		}

		public final String id;

		private RecipeType(String id)
		{
			this.id = id;
		}
	}

	public static final String[] DEFAULT_KEYS = { "#", "*", ".", "X", "Y", "Z", "$", "°", "§" };

	public static final String JSON_TYPE = "type", JSON_GROUP = "group", JSON_INGREDIENTS = "ingredients", JSON_PATTERN = "pattern", JSON_KEYS = "key",
			JSON_RESULT = "result", JSON_ITEM = "item", JSON_TAG = "tag", JSON_RESULT_COUNT = "count";

	/** Group of this Recipe. */
	private String group;
	/** Ingredients used in this Recipe. */
	private ArrayList<RecipeIngredient> ingredients;
	/** For Shaped recipes, associates each key used in the pattern to the index of an ingredient in the list of Ingredients. */
	private HashMap<String, Integer> keys;
	/** The name of this Recipe. */
	private String name;
	/** For Shaped recipes, the pattern to match. */
	private String[] pattern;
	/** The Item(s) given as a result of this Recipe. */
	private ItemStack result;
	/** Type of this Recipe. */
	private RecipeType type;

	public Recipe(String name)
	{
		this.name = name;
		this.type = RecipeType.SHAPED;
		this.ingredients = new ArrayList<>();
		this.keys = new HashMap<>();
		this.pattern = new String[] { "" };
	}

	public void addIngredient(RecipeIngredient ingredient)
	{
		this.addIngredient(this.newKey(), ingredient);
	}

	public void addIngredient(String key, RecipeIngredient ingredient)
	{
		if (!this.ingredients.contains(ingredient)) this.ingredients.add(ingredient);
		this.keys.put(key, this.ingredients.indexOf(ingredient));
	}

	public String getGroup()
	{
		return this.group;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Item> getIngredients()
	{
		return (ArrayList<Item>) this.ingredients.clone();
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Item> getKeys()
	{
		return (HashMap<String, Item>) this.keys.clone();
	}

	public String[] getPattern()
	{
		return this.pattern;
	}

	public ItemStack getResult()
	{
		return this.result;
	}

	@Override
	public String id()
	{
		return this.name;
	}

	public boolean isShaped()
	{
		return this.type == RecipeType.SHAPED;
	}

	public boolean isShapeless()
	{
		return this.type == RecipeType.SHAPELESS;
	}

	private String newKey()
	{
		int current = -1;
		boolean used = true;
		while (used)
		{
			++current;
			used = this.keys.containsKey(DEFAULT_KEYS[current]);
		}
		return DEFAULT_KEYS[current];
	}

	@Override
	public Recipe readJson(JsonValue json)
	{
		JsonObject root = json.asObject();
		this.type = RecipeType.find(root.getString(JSON_TYPE, "crafting_shaped"));
		this.group = root.getString(JSON_GROUP, null);

		if (this.isShaped())
		{
			if (root.get(JSON_PATTERN) != null)
			{
				ArrayList<String> pattern = new ArrayList<>();
				for (JsonValue p : root.get(JSON_PATTERN).asArray())
					pattern.add(p.asString());
				this.pattern = pattern.toArray(new String[pattern.size()]);
			}
			if (root.get(JSON_KEYS) != null)
			{
				for (Member member : root.get(JSON_KEYS).asObject())
					this.addIngredient(member.getName(), new RecipeIngredient().readJson(member.getValue()));
			}
		} else if (this.isShapeless() && root.get(JSON_INGREDIENTS) != null) for (JsonValue ingredient : root.get(JSON_INGREDIENTS).asArray())
			this.addIngredient(new RecipeIngredient().readJson(ingredient));

		if (root.get(JSON_RESULT) != null)
		{
			JsonObject result = root.get(JSON_RESULT).asObject();
			String id = result.getString(JSON_ITEM, null);
			int count = result.getInt(JSON_RESULT_COUNT, 1);
			if (id != null) this.result = new ItemStack(this.getVanillaPack().items.find(id), count);
		}

		return this;
	}

	public void removeIngredient(RecipeIngredient ingredient)
	{
		String key = null;
		int index = this.ingredients.indexOf(ingredient);
		for (String k : this.keys.keySet())
			if (this.keys.get(k) == index)
			{
				key = k;
				break;
			}
		this.removeKey(key);
		this.ingredients.remove(ingredient);
	}

	private void removeKey(String key)
	{
		this.keys.remove(key);
	}

	public void setGroup(String group)
	{
		this.group = group;
	}

	public void setPattern(String[] pattern)
	{
		this.pattern = pattern;
	}

	public void setResult(ItemStack result)
	{
		this.result = result;
	}

	@Override
	public JsonValue toJson()
	{
		JsonObject root = Json.object();
		root.add(JSON_TYPE, this.type.id);
		if (this.group != null) root.add(JSON_GROUP, this.group);

		if (this.isShaped())
		{
			JsonObject keys = Json.object();
			for (String key : this.keys.keySet())
				keys.add(key, this.ingredients.get(this.keys.get(key)).toJson());
			root.add(JSON_PATTERN, Json.array(this.pattern));
			root.add(JSON_KEYS, keys);
		} else if (this.isShapeless())
		{
			JsonArray ingredients = Json.array();
			for (RecipeIngredient ingredient : this.ingredients)
				ingredients.add(ingredient.toJson());
			root.add(JSON_INGREDIENTS, ingredients);
		}

		if (this.result != null)
		{
			JsonObject item = Json.object();
			item.add(JSON_ITEM, this.result.item.id);
			if (this.result.quantity != 1) item.add(JSON_RESULT_COUNT, this.result.quantity);
			root.add(JSON_RESULT, item);
		}
		return root;
	}

}
