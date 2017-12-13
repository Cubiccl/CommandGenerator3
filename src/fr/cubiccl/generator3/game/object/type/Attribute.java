package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.game.object.global.GlobalAttribute;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.Persistance;

public class Attribute extends GameObjectType
{

	public Attribute(String id)
	{
		super(id, Persistance.selectedVersion);
	}

	public GlobalAttribute globalValue()
	{
		return VersionTranslator.translator(this.version).attributes.inverse().get(this);
	}

}
