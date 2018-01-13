package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.BlockState;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

/** Furniture Blocks, with 4 data values starting at 2 determining which direction it's facing. */
public class BlockFurniture extends Block
{

	@Deprecated
	public static Text getName(String id, int damage)
	{
		if (damage >= 8) return new Text("block." + id + ".8.x", new Replacement("<facing>", new Text("utils.facing." + damage % 8)));
		return new Text("block." + id + ".x", new Replacement("<facing>", new Text("utils.facing." + damage)));
	}

	public BlockFurniture(String id)
	{
		super(id);
		this.addBlockState(new BlockState("facing", BlockState.STRING, "north", "south", "west", "east"));
	}
}
