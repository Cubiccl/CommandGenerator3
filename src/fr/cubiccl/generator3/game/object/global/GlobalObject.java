package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.util.Persistance;
import fr.cubiccl.generator3.util.Settings.Version;

/** Represents a global Object type, version independent.
 * 
 * @param <O> - The Object Type.
 * @param <I> - The Instance Type. */
public class GlobalObject
{

	/** This Object's ID. */
	public final String id;
	public final Version introduced;
	public final Version removed;

	public GlobalObject(String id)
	{
		this(id, Persistance.currentIntroduce, Persistance.currentRemoved);
	}

	public GlobalObject(String id, Version introduced, Version removed)
	{
		this.id = id;
		this.introduced = introduced;
		this.removed = removed;
	}

	/** @return True if this Object exists in the input Version. */
	public boolean exists(Version version)
	{
		return this.introduced.isBefore(version) && this.removed.isAfter(version);
	}

}
