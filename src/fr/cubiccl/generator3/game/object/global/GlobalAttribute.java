package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Attribute;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalAttribute extends GlobalObject
{
	public GlobalAttribute(String id)
	{
		super("attribute." + id);
	}

	public GlobalAttribute(String id, Version introduced, Version removed)
	{
		super("attribute." + id, introduced, removed);
	}

	public Attribute value(Version version)
	{
		return VersionTranslator.translator(version).attributes.get(this);
	}

}
