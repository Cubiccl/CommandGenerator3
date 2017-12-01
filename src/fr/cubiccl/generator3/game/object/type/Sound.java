package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.game.object.instance.GameObjectInstance;
import fr.cubiccl.generator3.util.Text;

public class Sound extends GameObjectType implements GameObjectInstance
{

	/** This Entity's ID. */
	public final String id;

	public Sound(String id)
	{
		this.id = id;
	}

	@Override
	public String id()
	{
		return this.id;
	}

	public Text name()
	{
		return new Text(this.id, false);
	}

	@Override
	public Sound duplicate()
	{
		return this;
	}

}
