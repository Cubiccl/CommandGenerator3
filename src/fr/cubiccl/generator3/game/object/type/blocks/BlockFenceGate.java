package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.BlockState;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

/** Fence Gate Blocks, with 8 data values determining which direction it's facing and whether it's open; and two states determining if it's powered and if it's in a wall. */
public class BlockFenceGate extends Block
{

	public static Text getName(String id, int damage)
	{
		return new Text("block." + id + ".x", new Replacement("<gate_status>", new Text("utils.gate_status." + damage)));
	}

	public BlockFenceGate(int idInt, String idString)
	{
		super(idInt, idString);
		this.addBlockState(new BlockState("facing", BlockState.STRING, 1, "south", "west", "north", "east"));
		this.addBlockState(new BlockState("open", BlockState.BOOLEAN, 4, "false", "true"));
		this.addBlockState(new BlockState("powered", BlockState.BOOLEAN, -1, "false", "true"));
		this.addBlockState(new BlockState("in_wall", BlockState.BOOLEAN, -1, "false", "true"));
		this.setTextureType(-4);
	}

	@Override
	public Text name(int damage)
	{
		return getName(this.id(), damage);
	}

}
