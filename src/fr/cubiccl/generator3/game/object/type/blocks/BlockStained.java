package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.BlockState;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

/** Stained Blocks, with 16 data values determining which color it is. */
public class BlockStained extends Block
{

	public static Text getName(String id, int damage)
	{
		return new Text("block." + id + ".x", new Replacement("<color>", new Text("utils.color." + damage)));
	}

	public BlockStained(int idInt, String idString)
	{
		super(idInt, idString);
		this.addBlockState(new BlockState("color", BlockState.STRING, 1, "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver",
				"cyan", "purple", "blue", "brown", "green", "red", "black"));
	}
	
	@Override
	public Text name(int damage)
	{
		return getName(this.id(), damage);
	}

}
