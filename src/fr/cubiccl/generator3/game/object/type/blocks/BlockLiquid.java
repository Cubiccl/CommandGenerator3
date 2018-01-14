package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.BlockState;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

/** Liquid Blocks, with 16 data values determining how much liquid it has. */
public class BlockLiquid extends Block
{

	@Deprecated
	public static Text getName(String id, int damage)
	{
		return new Text("block." + id + ".x", new Replacement("<liquid>", new Text("utils.liquid." + damage)));
	}

	public BlockLiquid(String id)
	{
		super(id, 0);
		this.addBlockState(new BlockState("level", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"));
	}

}
