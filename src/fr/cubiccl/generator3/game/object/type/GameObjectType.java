package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Settings.Version;
import fr.cubiccl.generator3.util.Text;

/** Parent class for any type of object in Minecraft. */
public abstract class GameObjectType implements Comparable<GameObjectType>
{

	public String id;
	public final Text name;
	public final Version version;

	public GameObjectType(String id, Version version)
	{
		this.id = id;
		this.version = version;
		this.name = this.createName();
	}

	protected Text createName()
	{
		return new Text(this.idPrefixless());
	}

	public String describe()
	{
		return this.type() + " " + this.id;
	}

	public String type()
	{
		return "???";
	}

	public String idPrefixless()
	{
		return this.id.replaceAll("minecraft:", "");
	}

	@Override
	public String toString()
	{
		return this.name.toString();
	}

}
