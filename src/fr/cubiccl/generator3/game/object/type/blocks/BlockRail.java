package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.v112.Blocks112;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

/** Redstone Rail Blocks, with 16 data values determining which shape it has and whether it's powered. */
public class BlockRail extends Block
{

	public static Text getName(String id, int damage)
	{
		return new Text("block." + id + ".x", new Replacement("<shape>", new Text("utils.rail." + damage)));
	}

	public BlockRail()
	{
		this(-1, null);
	}

	public BlockRail(int idInt, String idString)
	{
		super(idInt, idString);
		Blocks112.variant(this, "shape", 1, "north_south", "east_west", "ascending_east", "ascending_west", "ascending_north", "ascending_south");
		Blocks112.bool(this, "powered", 8);
	}

	@Override
	public Text name(int damage)
	{
		return getName(this.id(), damage);
	}

}
