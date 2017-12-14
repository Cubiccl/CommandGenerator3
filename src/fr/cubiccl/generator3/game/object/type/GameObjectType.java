package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.game.object.global.GlobalObject;
import fr.cubiccl.generator3.util.Settings.Version;
import fr.cubiccl.generator3.util.Text;

/** Parent class for any type of object in Minecraft. */
public abstract class GameObjectType implements Comparable<GameObjectType>
{

	public String id;
	public final Version version;

	public GameObjectType(String id, Version version)
	{
		this.id = id;
		this.version = version;
	}

	@Override
	public int compareTo(GameObjectType o)
	{
		return this.id.toLowerCase().compareTo(o.id.toLowerCase());
	}

	public abstract GlobalObject globalValue();

	public String idPrefixless()
	{
		return this.id.replaceAll("minecraft:", "");
	}

	public Text name()
	{
		GlobalObject global = this.globalValue();
		return global == null ? null : global.name;
	}

	@Override
	public String toString()
	{
		return this.name().toString();
	}

}
