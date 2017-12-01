package fr.cubiccl.generator3.game.object.type.items;

import fr.cubiccl.generator3.game.object.type.Item;
import fr.cubiccl.generator3.game.object.type.blocks.BlockStained;
import fr.cubiccl.generator3.util.Text;

/** This Item has 16 data values, determining which color it has. */
public class ItemStained extends Item
{

	public ItemStained(int idInt, String idString)
	{
		super(idInt, idString, 15);
	}

	@Override
	public Text name(int damage)
	{
		return BlockStained.getName(this.id(), damage);
	}

}
