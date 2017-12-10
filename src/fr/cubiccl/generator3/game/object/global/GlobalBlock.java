package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.instance.PlacedBlock;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalBlock extends GlobalObject
{
	public GlobalBlock(String id, int order)
	{
		super("block." + id, order);
	}

	public GlobalBlock(String id, int order, Version introduced, Version removed)
	{
		super("block." + id, order, introduced, removed);
	}

	public PlacedBlock value(Version version)
	{
		return VersionTranslator.translator(version).blocks.get(this);
	}

}
