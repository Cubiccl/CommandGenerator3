package fr.cubiccl.generator3.game.datapack;

import fr.cubiccl.generator3.game.datapack.DataPacks.Version;
import fr.cubiccl.generator3.game.object.data.Recipe;
import fr.cubiccl.generator3.game.object.data.Tag;
import fr.cubiccl.generator3.game.object.type.Attribute;
import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.Effect;
import fr.cubiccl.generator3.game.object.type.Enchantment;
import fr.cubiccl.generator3.game.object.type.Entity;
import fr.cubiccl.generator3.game.object.type.Item;
import fr.cubiccl.generator3.game.object.type.NBTTag;
import fr.cubiccl.generator3.game.object.type.Particle;
import fr.cubiccl.generator3.game.object.type.Sound;

public class DataPack
{

	public static class VanillaDataPack extends DataPack
	{

		public final GameObjectRegistry<Attribute> attributes = new GameObjectRegistry<Attribute>(this);
		public final GameObjectRegistry<Block> blocks = new GameObjectRegistry<Block>(this);
		public final GameObjectRegistry<Effect> effects = new GameObjectRegistry<Effect>(this);
		public final GameObjectRegistry<Enchantment> enchantments = new GameObjectRegistry<Enchantment>(this);
		public final GameObjectRegistry<Entity> entities = new GameObjectRegistry<Entity>(this);
		public final GameObjectRegistry<Item> items = new GameObjectRegistry<Item>(this);
		public final GameObjectRegistry<NBTTag> nbttags = new GameObjectRegistry<NBTTag>(this);
		public final GameObjectRegistry<Particle> particles = new GameObjectRegistry<Particle>(this);
		public final GameObjectRegistry<Sound> sounds = new GameObjectRegistry<Sound>(this);

		public VanillaDataPack(String name, int id)
		{
			super(name, id);
		}

	}

	public final DataObjectRegistry<Tag> blockTags = new DataObjectRegistry<Tag>(this);
	public final int id;
	public final DataObjectRegistry<Tag> itemTags = new DataObjectRegistry<Tag>(this);

	private String name;
	public final DataObjectRegistry<Recipe> recipes = new DataObjectRegistry<Recipe>(this);
	/** The Minecraft version this Pack is made for. */
	private Version version;

	public DataPack(String name, int id)
	{
		this.name = name;
		this.id = id;
		this.version = Version.latest();
	}

	public String getName()
	{
		return this.name;
	}

	public Version getVersion()
	{
		return this.version;
	}

	public boolean isVanillaPack()
	{
		return this instanceof VanillaDataPack;
	}

	public String namespace()
	{
		return this.isVanillaPack() ? "minecraft:" : (this.name + ":");
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setVersion(Version version)
	{
		this.version = version;
	}

	/** @return The Vanilla Data Pack this Data Pack is linked to. */
	public VanillaDataPack vanillaPack()
	{
		return this.version.getDataPack();
	}

}
