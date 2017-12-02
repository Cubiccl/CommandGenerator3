package fr.cubiccl.generator3.game.object.instance;

import fr.cubiccl.generator3.game.object.global.GlobalBlock;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.game.object.instance.nbt.TagCompound;
import fr.cubiccl.generator3.game.object.type.Block;

public class PlacedBlock implements GameObjectInstance
{

	/** The Block type. */
	public Block block;
	/** The NBT Tags if this is a Block Entity. */
	public TagCompound blockEntity;
	/** The Damage value. */
	public int damageValue;

	public PlacedBlock(Block block, int damageValue)
	{
		this(block, damageValue, null);
	}

	public PlacedBlock(Block block, int damageValue, TagCompound blockEntity)
	{
		this.block = block;
		this.damageValue = damageValue;
		this.blockEntity = blockEntity;
	}

	public GlobalBlock globalValue()
	{
		return VersionTranslator.translator(this.block.version).blocks.inverse().get(new PlacedBlock(this.block, this.damageValue));
	}

}
