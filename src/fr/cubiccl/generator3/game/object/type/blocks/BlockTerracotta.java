package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.BlockState;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

/** Terracotta Blocks, with 4 data values determining which direction it's facing. */
public class BlockTerracotta extends Block
{

	@Deprecated
	public static Text getName(String id, int damage)
	{
		return new Text("block." + id + ".x", new Replacement("<terra>", new Text("utils.terra." + damage)));
	}

	public BlockTerracotta(String id)
	{
		super(id, 0);
		this.addBlockState(new BlockState("facing", BlockState.STRING, "south", "west", "north", "east"));
	}

}
