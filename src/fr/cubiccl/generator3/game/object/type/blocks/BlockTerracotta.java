package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.v112.Blocks112;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

/** Terracotta Blocks, with 4 data values determining which direction it's facing. */
public class BlockTerracotta extends Block
{

	public static Text getName(String id, int damage)
	{
		return new Text("block." + id + ".x", new Replacement("<terra>", new Text("utils.terra." + damage)));
	}

	public BlockTerracotta(int idInt, String idString)
	{
		super(idInt, idString);
		Blocks112.variant(this, "facing", 1, "south", "west", "north", "east");
		this.setTextureType(-1);
	}

	@Override
	public Text name(int damage)
	{
		return getName(this.id(), damage);
	}

}
