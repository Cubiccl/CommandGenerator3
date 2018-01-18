package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.Versions.Version;
import fr.cubiccl.generator3.game.object.type.GameObjectType;
import fr.cubiccl.generator3.util.Text;

/** Represents a global Object type, version independent.
 * 
 * @param <O> - The Object Type.
 * @param <I> - The Instance Type. */
public abstract class GlobalObject implements Comparable<GlobalObject>
{

	/** This Object's ID. */
	public final String id;
	/** This Object's name. */
	public final Text name;
	/** This Object's order relative to the other objects of the same type. Used for sorting. */
	public int order;

	public GlobalObject(String id, int order)
	{
		this.id = id;
		this.order = order;
		this.name = this.createName();
	}

	@Override
	public int compareTo(GlobalObject o)
	{
		return Double.compare(this.order, o.order);
	}

	protected Text createName()
	{
		return new Text(this.id);
	}

	/** @return True if this Object exists in the input Version. */
	public boolean exists(Version version)
	{
		if (version == null) return true;
		return VersionTranslator.translator(version).exists(this);
	}

	public String idPrefixless()
	{
		if (this.prefix() == null) return this.id;
		return this.id.replaceAll(this.prefix() + "\\.", "");
	}

	protected abstract String prefix();

	@Override
	public String toString()
	{
		return this.name.toString();
	}

	public abstract GameObjectType value(Version version);

}
