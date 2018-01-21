package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.game.object.GameObject;
import fr.cubiccl.generator3.util.Text;

/** Parent class for any type of object in Minecraft. */
public abstract class GameObjectType extends GameObject implements Comparable<GameObjectType>
{

	private String id;
	public final Text name;

	public GameObjectType(String id)
	{
		this.id = id;
		this.name = this.createName();
	}

	protected Text createName()
	{
		return new Text(this.idWithoutNamespace());
	}

	public String describe()
	{
		return this.type() + " " + this.id;
	}

	public String id()
	{
		return (this.usesPrefix() ? this.getDatapack().namespace() : "") + this.idWithoutNamespace();
	}

	public String idWithoutNamespace()
	{
		return this.id;
	}

	@Override
	public String toString()
	{
		return this.name.toString();
	}

	public String type()
	{
		return "???";
	}

	protected boolean usesPrefix()
	{
		return true;
	}

}
