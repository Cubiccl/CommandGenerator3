package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Attribute;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalAttribute extends GlobalObject
{
	public GlobalAttribute(String id, double order)
	{
		super("attribute." + id, order);
	}

	public GlobalAttribute(String id, double order, Version introduced, Version removed)
	{
		super("attribute." + id, order, introduced, removed);
	}

	public Attribute value(Version version)
	{
		return VersionTranslator.translator(version).attributes.get(this);
	}

}
