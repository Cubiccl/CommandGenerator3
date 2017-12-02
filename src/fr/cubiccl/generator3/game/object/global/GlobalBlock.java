package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.instance.PlacedBlock;
import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalBlock extends GlobalObject
{
	public GlobalBlock(String id)
	{
		super("block." + id);
	}

	public GlobalBlock(String id, Version introduced, Version removed)
	{
		super("block." + id, introduced, removed);
	}

	public PlacedBlock value(Version version)
	{
		return VersionTranslator.translator(version).blocks.get(this);
	}

	public Block valueAsGroup(Version version)
	{
		return VersionTranslator.translator(version).blockGroups.get(this);
	}

}
