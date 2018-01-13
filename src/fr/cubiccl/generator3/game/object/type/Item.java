package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.game.object.global.GlobalItem;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.Persistance;

public class Item extends GameObjectType
{

	/** <code>true</code> if this Item has durability. */
	public final int durability;

	public Item(String id)
	{
		this(id, 0);
	}

	public Item(String id, int durability)
	{
		super("minecraft:" + id, Persistance.selectedVersion);
		this.durability = durability;
	}

	public GlobalItem globalValue()
	{
		return VersionTranslator.translator(this.version).items.inverse().get(this);
	}

}
