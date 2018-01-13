package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.game.object.global.GlobalSound;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.Persistance;

public class Sound extends GameObjectType
{

	public Sound(String id)
	{
		super(id, Persistance.selectedVersion);
	}

	public GlobalSound globalValue()
	{
		return VersionTranslator.translator(this.version).sounds.inverse().get(this);
	}

}
