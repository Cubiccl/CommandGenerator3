package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.v112.Blocks112;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

/** Stairs Blocks, with 8 data values determining which direction it's facing, and a state describing its shape. */
public class BlockStairs extends Block
{

	public static Text getName(String id, int damage)
	{
		return new Text("block." + id + ".x", new Replacement("<orientation>", new Text("utils.stairs." + damage)));
	}

	public BlockStairs(int idInt, String idString)
	{
		super(idInt, idString);
		Blocks112.variant(this, "facing", 1, "east", "west", "south", "north");
		Blocks112.variant(this, "half", 4, "bottom", "top");
		Blocks112.variant(this, "shape", -1, "straight", "inner_left", "inner_right", "outer_left", "outer_right");
		this.setTextureType(-4);
	}

	@Override
	public Text name(int damage)
	{
		return getName(this.id(), damage);
	}

}
