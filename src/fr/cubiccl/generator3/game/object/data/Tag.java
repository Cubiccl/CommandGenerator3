package fr.cubiccl.generator3.game.object.data;

import java.util.ArrayList;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/** A list of Objects the game uses. */
public class Tag extends DataObject
{
	public static class TagContent
	{
		private TagContentType type;
		private String value;

		public TagContent()
		{
			this(TagContentType.OBJECT_ID, "");
		}

		public TagContent(TagContentType type, String value)
		{
			super();
			this.type = type;
			this.value = value;
		}

		public TagContentType getType()
		{
			return type;
		}

		public String getValue()
		{
			return value;
		}

		public void setType(TagContentType type)
		{
			this.type = type;
		}

		public void setValue(String value)
		{
			this.value = value;
		}

		public String toJson()
		{
			return (this.type == TagContentType.OTHER_TAG ? "#" : "") + this.value;
		}
	}

	public static enum TagContentType
	{
		OBJECT_ID,
		OTHER_TAG;
	}

	public static enum TagType
	{
		BLOCK,
		ITEM;
	}

	public static final String JSON_REPLACE = "replace", JSON_VALUES = "values";

	private String name;
	/** True if this Tag completely replaces values from its parents. */
	private boolean replace;
	private TagType type;
	private final ArrayList<TagContent> values;

	public Tag(String name)
	{
		this(name, TagType.BLOCK);
	}

	public Tag(String name, TagType type)
	{
		this(name, type, false);
	}

	public Tag(String name, TagType type, boolean replace)
	{
		this.name = name;
		this.type = type;
		this.replace = replace;
		this.values = new ArrayList<>();
	}

	public void addValue(TagContent value)
	{
		this.values.add(value);
	}

	public String getName()
	{
		return name;
	}

	public TagType getType()
	{
		return type;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<TagContent> getValues()
	{
		return (ArrayList<TagContent>) values.clone();
	}

	@Override
	public String id()
	{
		return this.name;
	}

	public boolean isReplace()
	{
		return replace;
	}

	public void moveValue(TagContent value, int to)
	{
		this.values.remove(value);
		this.values.add(to, value);
	}

	@Override
	public Tag readJson(JsonValue json)
	{
		JsonObject root = json.asObject();
		this.replace = root.getBoolean("replace", false);
		this.values.clear();
		if (root.get("values") != null)
		{
			for (JsonValue value : root.get("values").asArray())
			{
				boolean isTag = value.asString().startsWith("#");
				this.values.add(new TagContent(isTag ? TagContentType.OTHER_TAG : TagContentType.OBJECT_ID, value.asString().substring(isTag ? 1 : 0)));
			}
		}

		return this;
	}

	public void removeValue(TagContent value)
	{
		this.values.remove(value);
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setReplace(boolean replace)
	{
		this.replace = replace;
	}

	public void setType(TagType type)
	{
		this.type = type;
	}

	@Override
	public JsonValue toJson()
	{
		JsonObject value = Json.object();
		value.add(JSON_REPLACE, this.replace);

		ArrayList<String> v = new ArrayList<>();
		for (TagContent content : this.values)
			v.add(content.toJson());
		value.add(JSON_VALUES, Json.array(v.toArray(new String[v.size()])));

		return value;
	}

}
