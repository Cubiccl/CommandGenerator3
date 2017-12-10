package fr.cubiccl.generator3.game.object.global;

import com.google.common.collect.HashBiMap;

import fr.cubiccl.generator3.game.object.type.*;
import fr.cubiccl.generator3.util.Settings.Version;

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

}
