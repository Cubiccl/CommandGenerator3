package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Text;

/** Parent class for any type of object in Minecraft. */
public abstract class GameObjectType implements Comparable<GameObjectType>
{

	public String id;
	public final Text name;

	public GameObjectType(String id)
	{
		this.id = id;
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
