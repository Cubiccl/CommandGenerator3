package fr.cubiccl.generator3.game.object.type;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import fr.cubiccl.generator3.game.object.global.GlobalBlock;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.*;

public class Block extends GameObjectType
{

	/** The possible {@link BlockState Block states} for this Block. */
	public HashMap<String, BlockState> blockStates;
	/** ID of this Block. */
	public final String id;

	public Block(String id)
	{
		super(Persistance.selectedVersion);
		this.id = "minecraft:" + id;
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

	@Override
	public String id()
	{
		return this.id;
	}

	@Override
	public Text name()
	{
		String nameID = this.id;
		if (Lang.keyExists("block." + nameID)) return new Text("block." + nameID);
		if (Lang.keyExists("item." + nameID)) return new Text("item." + nameID);
		Logger.log("Couldn't find translation for : block." + nameID);
		return new Text("block." + nameID);
	}

	/** @param damage - A damage value.
	 * @return The name of this Block for the input damage value. */
	public BufferedImage texture()
	{
		return Textures.getTexture("block." + this.id);
	}

}
