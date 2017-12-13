package fr.cubiccl.generator3.game.object.type;

import java.util.HashMap;

import fr.cubiccl.generator3.game.object.global.GlobalBlock;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.Persistance;

public class Block extends GameObjectType
{

	/** The possible {@link BlockState Block states} for this Block. */
	public HashMap<String, BlockState> blockStates;

	public Block(String id)
	{
		super("minecraft:" + id, Persistance.selectedVersion);
		this.blockStates = new HashMap<String, BlockState>();
	}

	/** Adds a State to this Block.
	 * 
	 * @param state - The Block state to add. */
	public void addBlockState(BlockState state)
	{
		this.blockStates.put(state.id, state);
	}

	public GlobalBlock globalValue()
	{
		return VersionTranslator.translator(this.version).blocks.inverse().get(this);
	}

}
