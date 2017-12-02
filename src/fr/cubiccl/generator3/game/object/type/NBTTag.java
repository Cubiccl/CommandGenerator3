package fr.cubiccl.generator3.game.object.type;

import java.util.ArrayList;
import java.util.Arrays;

import fr.cubiccl.generator3.game.object.global.GlobalNBTTag;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.Lang;
import fr.cubiccl.generator3.util.Persistance;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

public abstract class NBTTag extends GameObjectType
{

	public static class asBoolean extends NBTTag
	{
		public asBoolean(String id, GameObjectType... applicable)
		{
			super(id, BOOLEAN, applicable);
		}
	}

	public static class asByte extends NBTTag
	{
		public asByte(String id, GameObjectType... applicable)
		{
			super(id, BYTE, applicable);
		}
	}

	public static class asCompound extends NBTTag
	{
		public asCompound(String id, GameObjectType... applicable)
		{
			super(id, COMPOUND, applicable);
		}
	}

	public static class asDouble extends NBTTag
	{
		public asDouble(String id, GameObjectType... applicable)
		{
			super(id, DOUBLE, applicable);
		}
	}

	public static class asFloat extends NBTTag
	{
		public asFloat(String id, GameObjectType... applicable)
		{
			super(id, FLOAT, applicable);
		}
	}

	public static class asInt extends NBTTag
	{
		public asInt(String id, GameObjectType... applicable)
		{
			super(id, INT, applicable);
		}
	}

	public static class asList extends NBTTag
	{
		public asList(String id, GameObjectType... applicable)
		{
			super(id, LIST, applicable);
		}
	}

	public static class asLong extends NBTTag
	{
		public asLong(String id, GameObjectType... applicable)
		{
			super(id, LONG, applicable);
		}
	}

	public static class asShort extends NBTTag
	{
		public asShort(String id, GameObjectType... applicable)
		{
			super(id, SHORT, applicable);
		}
	}

	public static class asString extends NBTTag
	{
		public asString(String id, GameObjectType... applicable)
		{
			super(id, STRING, applicable);
		}
	}

	/** Identifiers for Tag types. */
	public static final byte BOOLEAN = 0, BYTE = 1, SHORT = 2, INT = 3, LONG = 4, FLOAT = 5, DOUBLE = 6, STRING = 7, COMPOUND = 8, LIST = 9;

	/** The Object IDs this Tag can be applied to. */
	public final ArrayList<GameObjectType> applicable;
	/** This NBT Tag's ID. */
	public final String id;
	/** This NBT Tag's type. */
	public final byte type;

	public NBTTag(String id, byte type, GameObjectType... applicable)
	{
		super(Persistance.selectedVersion);
		this.id = id;
		this.type = type;
		this.applicable = new ArrayList<GameObjectType>(Arrays.asList(applicable));
	}

	/** @return True if this tag can be applied to the Object with the input ID. */
	public boolean canApplyTo(GameObjectType object)
	{
		if (object == null) return true;
		return this.applicable.contains(object);
	}

	/** @param object - The Object this Tag is applied to.
	 * @return A description of this NBT Tag. */
	public Text description(GameObjectType object)
	{
		String d = "tag." + this.id;

		Text t = new Text(d);
		if (object != null)
		{
			String objectSpecific = d + "." + object.id();
			if (Lang.keyExists(objectSpecific)) return new Text(objectSpecific, new Replacement("<o>", object.name()));
			t.addReplacement("<o>", object.name());
		}
		return t;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof NBTTag)) return false;
		NBTTag o = (NBTTag) obj;

		return this.type == o.type && this.id().equals(o.id());
	}

	public GlobalNBTTag globalValue()
	{
		return VersionTranslator.translator(this.version).nbtTags.inverse().get(this);
	}

	@Override
	public String id()
	{
		return this.id;
	}

	@Override
	public Text name()
	{
		return new Text(this.id, false);
	}

}
