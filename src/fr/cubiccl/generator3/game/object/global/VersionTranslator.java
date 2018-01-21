package fr.cubiccl.generator3.game.object.global;

import com.google.common.collect.HashBiMap;

import fr.cubiccl.generator3.game.datapack.DataPacks.Version;
import fr.cubiccl.generator3.game.object.type.Attribute;
import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.Effect;
import fr.cubiccl.generator3.game.object.type.Enchantment;
import fr.cubiccl.generator3.game.object.type.Entity;
import fr.cubiccl.generator3.game.object.type.Item;
import fr.cubiccl.generator3.game.object.type.NBTTag;
import fr.cubiccl.generator3.game.object.type.Particle;
import fr.cubiccl.generator3.game.object.type.Sound;

public class VersionTranslator
{

	public static final VersionTranslator v113 = new VersionTranslator();

	public static VersionTranslator translator(Version version)
	{
		if (version == Version.v113) return v113;
		return null;
	}

	public final HashBiMap<GlobalAttribute, Attribute> attributes = HashBiMap.create();
	public final HashBiMap<GlobalBlock, Block> blocks = HashBiMap.create();
	public final HashBiMap<GlobalEffect, Effect> effects = HashBiMap.create();
	public final HashBiMap<GlobalEnchantment, Enchantment> enchantments = HashBiMap.create();
	public final HashBiMap<GlobalEntity, Entity> entities = HashBiMap.create();
	public final HashBiMap<GlobalItem, Item> items = HashBiMap.create();
	public final HashBiMap<GlobalNBTTag, NBTTag> nbtTags = HashBiMap.create();
	public final HashBiMap<GlobalParticle, Particle> particles = HashBiMap.create();
	public final HashBiMap<GlobalSound, Sound> sounds = HashBiMap.create();

	public boolean exists(GlobalObject object)
	{
		if (object instanceof GlobalAttribute) return this.attributes.containsKey(object);
		if (object instanceof GlobalBlock) return this.blocks.containsKey(object);
		if (object instanceof GlobalEffect) return this.effects.containsKey(object);
		if (object instanceof GlobalEnchantment) return this.enchantments.containsKey(object);
		if (object instanceof GlobalEntity) return this.entities.containsKey(object);
		if (object instanceof GlobalItem) return this.items.containsKey(object);
		if (object instanceof GlobalNBTTag) return this.nbtTags.containsKey(object);
		if (object instanceof GlobalParticle) return this.particles.containsKey(object);
		if (object instanceof GlobalSound) return this.sounds.containsKey(object);
		return false;
	}

}
