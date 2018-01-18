package fr.cubiccl.generator3.game.object.type;

import java.util.HashMap;

import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Textures;
import javafx.scene.image.Image;

public class Block extends GameObjectType
{

	/** The possible {@link BlockState Block states} for this Block. */
	public HashMap<String, BlockState> blockStates;
	public final int order;
	public final Image texture;

	public Block(String id, int order)
	{
		super("minecraft:" + id);
		this.order = order;
		this.blockStates = new HashMap<String, BlockState>();
		this.texture = Textures.getTexture("block." + this.idPrefixless());
	}

	/** Adds a State to this Block.
	 * 
	 * @param state - The Block state to add. */
	public void addBlockState(BlockState state)
	{
		this.blockStates.put(state.id, state);
	}

	@Override
	public int compareTo(GameObjectType o)
	{
		if (!(o instanceof Block)) return 0;
		return Integer.compare(this.order, ((Block) o).order);
	}

	@Override
	protected Text createName()
	{
		return new Text("block." + this.idPrefixless());
	}

	@Override
	public String describe()
	{
		String desc = super.describe() + "[";
		boolean s = false, v = false;
		for (BlockState state : this.blockStates.values())
		{
			v = false;
			if (s) desc += ", ";
			else s = true;

			desc += "(";
			for (String value : state.values)
			{
				if (v) desc += ",";
				else v = true;
				desc += value;
			}
			desc += ")";
		}
		desc += "]";
		return desc;
	}

	@Override
	public String type()
	{
		return "Block";
	}

}
