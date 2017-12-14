package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Item;
import fr.cubiccl.generator3.util.Lang;
import fr.cubiccl.generator3.util.Settings.Version;
import fr.cubiccl.generator3.util.Text;

public class GlobalItem extends GlobalObject
{
	public GlobalItem(String id, int order)
	{
		super("item." + id, order);
	}

	@Override
	protected Text createName()
	{
		String b = "block." + this.idPrefixless();
		if (Lang.keyExists(b)) return new Text(b);
		return super.createName();
	}

	@Override
	protected String prefix()
	{
		return "item";
	}

	public Item value(Version version)
	{
		return VersionTranslator.translator(version).items.get(this);
	}

}
