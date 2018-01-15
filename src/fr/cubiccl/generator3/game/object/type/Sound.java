package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Persistance;
import fr.cubiccl.generator3.util.Text;

public class Sound extends GameObjectType
{

	public Sound(String id)
	{
		super(id, Persistance.selectedVersion);
	}

	@Override
	public int compareTo(GameObjectType o)
	{
		if ((o instanceof Sound)) return 0;
		return this.id.compareTo(((Sound) o).id);
	}

	@Override
	protected Text createName()
	{
		return new Text("sound." + this.idPrefixless());
	}

	@Override
	public String type()
	{
		return "Sound";
	}

}
