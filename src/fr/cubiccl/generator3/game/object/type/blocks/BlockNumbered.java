package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

/** This Block has several data values, the number displays in the name. */
public class BlockNumbered extends Block
{

	@Deprecated
	public static Text getName(String id, int damage)
	{
		int actual = damage + 1;
		if (id.contains("weighted_") || id.contains("_wire")) --actual;
		Text t = new Text("block." + id + "." + damage, new Replacement("<count>", Integer.toString(actual)));
		if (t.isTranslated()) return t;
		return new Text("block." + id + ".x", new Replacement("<count>", Integer.toString(actual)));
	}

	public BlockNumbered(String id)
	{
		super(id, 0);
	}

}
