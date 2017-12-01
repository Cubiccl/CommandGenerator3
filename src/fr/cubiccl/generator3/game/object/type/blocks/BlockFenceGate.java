package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.v112.Blocks112;
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
		Blocks112.variant(this, "facing", 1, "south", "west", "north", "east");
		Blocks112.bool(this, "open", 4);
		Blocks112.bool(this, "powered", -1);
		Blocks112.bool(this, "in_wall", -1);
		this.setTextureType(-4);
	}

	@Override
	public Text name(int damage)
	{
		return getName(this.id(), damage);
	}

}
