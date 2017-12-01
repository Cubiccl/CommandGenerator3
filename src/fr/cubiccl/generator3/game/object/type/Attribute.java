package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.game.object.instance.GameObjectInstance;
import fr.cubiccl.generator3.util.Text;

public class Attribute extends GameObjectType implements GameObjectInstance
{

	/** This Achievement's ID. */
	public final String id;

	public Attribute(String id)
	{
		this.id = id;
	}

	@Override
	public Attribute duplicate()
	{
		return this;
	}

	@Override
	public String id()
	{
		return this.id;
	}

	@Override
	public Text name()
	{
		return new Text("attribute." + this.id);
	}

}
