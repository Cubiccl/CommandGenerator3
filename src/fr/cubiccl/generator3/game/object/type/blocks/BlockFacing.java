package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.BlockState;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

/** Directional Blocks, with 6 data values determining which direction it's facing (including up and down). */
public class BlockFacing extends Block
{

	@Deprecated
	public static Text getName(String id, int damage)
	{
		if (damage >= 8) return new Text("block." + id + ".8.x", new Replacement("<facing>", new Text("utils.facing." + damage % 8)));
		return new Text("block." + id + ".x", new Replacement("<facing>", new Text("utils.facing." + damage)));
	}

	public BlockFacing(String id)
	{
		super(id, 0);
		this.addBlockState(new BlockState("facing", "down", "up", "north", "south", "west", "east"));
	}

}
