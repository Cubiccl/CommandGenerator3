package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.util.Settings.Version;
import fr.cubiccl.generator3.util.Text;

/** Represents a global Object type, version independent.
 * 
 * @param <O> - The Object Type.
 * @param <I> - The Instance Type. */
public class GlobalObject implements Comparable<GlobalObject>
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
		this.name = new Text(this.id);
	}

	@Override
	public int compareTo(GlobalObject o)
	{
		return Double.compare(this.order, o.order);
	}

	/** @return True if this Object exists in the input Version. */
	public boolean exists(Version version)
	{
		if (version == null) return true;
		return VersionTranslator.translator(version).exists(this);
	}

	@Override
	public String toString()
	{
		return this.name.toString();
	}

}
