package fr.cubiccl.generator3.game.object.instance;

import fr.cubiccl.generator3.game.object.instance.nbt.TagCompound;
import fr.cubiccl.generator3.game.object.type.Entity;

public class LivingEntity implements GameObjectInstance
{

	/** The Entity type. */
	public final Entity entity;
	/** The NBT Tags of this Entity. */
	public final TagCompound nbtTags;

	public LivingEntity(Entity entity)
	{
		this(entity, null);
	}

	public LivingEntity(Entity entity, TagCompound nbtTags)
	{
		this.entity = entity;
		this.nbtTags = nbtTags;
	}

}
