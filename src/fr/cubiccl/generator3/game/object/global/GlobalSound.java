package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Sound;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalSound extends GlobalObject
{
	public GlobalSound(String id, int order)
	{
		super("sound." + id, order);
	}

	@Override
	protected Text createName()
	{
		return new Text(this.id, false);
	}

	@Override
	protected String prefix()
	{
		return "sound";
	}

	public Sound value(Version version)
	{
		return VersionTranslator.translator(version).sounds.get(this);
	}

}
