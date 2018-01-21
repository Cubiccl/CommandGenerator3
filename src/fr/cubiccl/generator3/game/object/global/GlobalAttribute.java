package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.datapack.DataPacks.Version;
import fr.cubiccl.generator3.game.object.type.Attribute;

public class GlobalAttribute extends GlobalObject
{
	public GlobalAttribute(String id, int order)
	{
		super("attribute." + id, order);
	}

	@Override
	protected String prefix()
	{
		return "attribute";
	}

	public Attribute value(Version version)
	{
		return VersionTranslator.translator(version).attributes.get(this);
	}

}
