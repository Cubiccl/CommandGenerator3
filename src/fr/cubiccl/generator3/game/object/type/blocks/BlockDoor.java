package fr.cubiccl.generator3.game.object.type.blocks;

import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.v112.Blocks112;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;
import fr.cubiccl.generator3.util.Utils;

/** Door Blocks, with 11 data values and various states describing its status. */
public class BlockDoor extends Block
{

	public static Text getName(String id, int damage)
	{
		return new Text("block." + id + ".x", new Replacement("<door_status>", new Text("utils.door_status." + damage)));
	}

	public BlockDoor(int idInt, String idString)
	{
		super(idInt, idString);
		Blocks112.variant(this, "facing", -1, "north", "south", "west", "east");
		Blocks112.variant(this, "half", -1, "lower", "upper");
		Blocks112.variant(this, "hinge", -1, "left", "right");
		Blocks112.bool(this, "open", -1);
		Blocks112.bool(this, "powered", -1);
		this.damageValues = Utils.generateArray(11);
		this.setTextureType(-8);
	}

	@Override
	public Text name(int damage)
	{
		return getName(this.id(), damage);
	}

}
