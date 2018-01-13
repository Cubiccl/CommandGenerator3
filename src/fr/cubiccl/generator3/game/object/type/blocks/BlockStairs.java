package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.BlockState;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

/** Stairs Blocks, with 8 data values determining which direction it's facing, and a state describing its shape. */
public class BlockStairs extends Block
{

	@Deprecated
	public static Text getName(String id, int damage)
	{
		return new Text("block." + id + ".x", new Replacement("<orientation>", new Text("utils.stairs." + damage)));
	}

	public BlockStairs(String id)
	{
		super(id);
		this.addBlockState(new BlockState("facing", BlockState.STRING, "east", "west", "south", "north"));
		this.addBlockState(new BlockState("half", BlockState.STRING, "bottom", "top"));
		this.addBlockState(new BlockState("shape", BlockState.STRING, "straight", "inner_left", "inner_right", "outer_left", "outer_right"));
	}

}
