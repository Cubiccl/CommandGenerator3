package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.game.object.global.GlobalEntity;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.Persistance;

public class Entity extends GameObjectType
{

	public Entity(String id)
	{
		super("minecraft:" + id, Persistance.selectedVersion);
	}

	public GlobalEntity globalValue()
	{
		return VersionTranslator.translator(this.version).entities.inverse().get(this);
	}

}
