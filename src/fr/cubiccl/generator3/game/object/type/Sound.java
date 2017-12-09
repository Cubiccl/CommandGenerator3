package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.game.object.global.GlobalSound;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.Persistance;
import fr.cubiccl.generator3.util.Text;

public class Sound extends GameObjectType
{

	/** This Entity's ID. */
	public final String id;

	public Sound(String id)
	{
		super(Persistance.selectedVersion);
		this.id = id;
		Sounds112.values.add(this);
	}

	public GlobalSound globalValue()
	{
		return VersionTranslator.translator(this.version).sounds.inverse().get(this);
	}

	@Override
	public String id()
	{
		return this.id;
	}

	@Override
	public Text name()
	{
		return new Text(this.id, false);
	}

}
