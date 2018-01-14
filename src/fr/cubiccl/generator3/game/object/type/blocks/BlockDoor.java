package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.BlockState;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

/** Door Blocks, with 11 data values and various states describing its status. */
public class BlockDoor extends Block
{

	@Deprecated
	public static Text getName(String id, int damage)
	{
		return new Text("block." + id + ".x", new Replacement("<door_status>", new Text("utils.door_status." + damage)));
	}

	public BlockDoor(int idInt, String idString)
	{
		super(idString, 0);
		this.addBlockState(new BlockState("facing", BlockState.STRING, "north", "south", "west", "east"));
		this.addBlockState(new BlockState("half", BlockState.STRING, "lower", "upper"));
		this.addBlockState(new BlockState("hinge", BlockState.STRING, "left", "right"));
		this.addBlockState(new BlockState("open", BlockState.BOOLEAN, "false", "true"));
		this.addBlockState(new BlockState("powered", BlockState.BOOLEAN, "false", "true"));
	}

}
