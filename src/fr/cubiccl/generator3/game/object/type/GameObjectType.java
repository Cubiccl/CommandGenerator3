package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Settings.Version;
import fr.cubiccl.generator3.util.Text;

/** Parent class for any type of object in Minecraft. */
public abstract class GameObjectType
{

	public final Version version;

	public GameObjectType(Version version)
	{
		this.version = version;
	}

	/** @return This Object type's ID. */
	public abstract String id();

	/** @return This Object's name. */
	public abstract Text name();

}
