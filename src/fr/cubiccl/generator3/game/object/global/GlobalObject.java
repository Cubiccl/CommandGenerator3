package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.util.Persistance;
import fr.cubiccl.generator3.util.Settings.Version;

/** Represents a global Object type, version independent.
 * 
 * @param <O> - The Object Type.
 * @param <I> - The Instance Type. */
public class GlobalObject implements Comparable<GlobalObject>
{

	/** This Object's ID. */
	public final String id;
	public final Version introduced;
	public int order;
	public final Version removed;

	public GlobalObject(String id, int order)
	{
		this(id, order, Persistance.currentIntroduce, Persistance.currentRemoved);
	}

	public GlobalObject(String id, int order, Version introduced, Version removed)
	{
		this.id = id;
		this.order = order;
		this.introduced = introduced;
		this.removed = removed;
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
		return this.introduced.isBefore(version) && this.removed.isAfter(version);
	}

	@Override
	public String toString()
	{
		return this.id;
	}

}
