package fr.cubiccl.generator3.game.object.global;

import java.util.HashMap;

import fr.cubiccl.generator3.game.object.instance.GameObjectInstance;
import fr.cubiccl.generator3.game.object.type.GameObjectType;
import fr.cubiccl.generator3.util.Logger;
import fr.cubiccl.generator3.util.Settings.Version;

/** Represents a global Object type, version independent.
 * 
 * @param <O> - The Object Type.
 * @param <I> - The Instance Type. */
public class GlobalObjectType<O extends GameObjectType, I extends GameObjectInstance>
{

	/** This Object's ID. */
	public final String id;
	/** Associates each version with the value this Object will have. */
	private final HashMap<Version, I> instanceTable;
	/** Associates each version with the type this Object will have. */
	private final HashMap<Version, O> typeTable;

	public GlobalObjectType(String id)
	{
		this.id = id;
		this.instanceTable = new HashMap<Version, I>();
		this.typeTable = new HashMap<Version, O>();
	}

	@SuppressWarnings("unchecked")
	public I instance(Version version)
	{
		return (I) this.instanceTable.get(version).duplicate();
	}

	@SuppressWarnings("unchecked")
	public GlobalObjectType<O, I> setVersion(Version version, O object)
	{
		if (object instanceof GameObjectInstance)
		{
			this.typeTable.put(version, object);
			this.instanceTable.put(version, (I) object);
		} else Logger.log(object.name() + " is not an Object Instance!");
		return this;
	}

	public GlobalObjectType<O, I> setVersion(Version version, O type, I instance)
	{
		this.typeTable.put(version, type);
		this.instanceTable.put(version, instance);
		return this;
	}

	public O type(Version version)
	{
		return this.typeTable.get(version);
	}

}
