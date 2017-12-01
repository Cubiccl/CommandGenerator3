package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.v112.Blocks112;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

/** This Block has 6 data values, determining which type of wood it is made of. */
public class BlockWood extends Block
{

	public static Text getName(String id, int damage)
	{
		if (damage >= 8) return new Text("block." + id + ".8.x", new Replacement("<wood>", new Text("utils.wood." + damage % 8)));
		return new Text("block." + id + ".x", new Replacement("<wood>", new Text("utils.wood." + damage)));
	}

	public BlockWood(int idInt, String idString)
	{
		super(idInt, idString);
		Blocks112.variant(this, this.id().contains("planks") ? "variant" : "type", 1, "oak", "spruce", "birch", "jungle", "acacia", "dark_oak");
		this.setTextureType(8);
	}

	@Override
	public Text name(int damage)
	{
		return getName(this.id(), damage);
	}

}
