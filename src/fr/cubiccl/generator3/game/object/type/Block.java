package fr.cubiccl.generator3.game.object.type;

import java.util.HashMap;

import fr.cubiccl.generator3.util.Persistance;

public class Block extends GameObjectType
{

	/** The possible {@link BlockState Block states} for this Block. */
	public HashMap<String, BlockState> blockStates;
	public final int idNum;

	public Block(String id, int idNum)
	{
		super("minecraft:" + id, Persistance.selectedVersion);
		this.idNum = idNum;
		this.blockStates = new HashMap<String, BlockState>();
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
		return Integer.compare(this.idNum, ((Block) o).idNum);
	}

}
