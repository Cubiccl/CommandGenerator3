package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Attribute;
import fr.cubiccl.generator3.util.Settings.Version;

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
