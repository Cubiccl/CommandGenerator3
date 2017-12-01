package fr.cubiccl.generator3.game.object.type.items;

import fr.cubiccl.generator3.game.object.type.Item;
import fr.cubiccl.generator3.game.object.type.blocks.BlockWood;
import fr.cubiccl.generator3.util.Text;

/** This Item has 6 data values, determining which type of wood it is made of. */
public class ItemWood extends Item
{

	public ItemWood(int idInt, String idString)
	{
		super(idInt, idString, 5);
	}

	@Override
	public Text name(int damage)
	{
		return BlockWood.getName(this.id(), damage);
	}

}
