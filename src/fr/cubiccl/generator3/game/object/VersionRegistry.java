package fr.cubiccl.generator3.game.object;

import fr.cubiccl.generator3.game.object.type.*;
import fr.cubiccl.generator3.util.Settings.Version;

public class VersionRegistry
{

	public final Version version;

	public final GameObjectRegistry<Attribute> attributes = new GameObjectRegistry<Attribute>();
	public final GameObjectRegistry<Block> blocks = new GameObjectRegistry<Block>();
	public final GameObjectRegistry<Effect> effects = new GameObjectRegistry<Effect>();
	public final GameObjectRegistry<Enchantment> enchantments = new GameObjectRegistry<Enchantment>();
	public final GameObjectRegistry<Entity> entities = new GameObjectRegistry<Entity>();
	public final GameObjectRegistry<Item> items = new GameObjectRegistry<Item>();
	public final GameObjectRegistry<NBTTag> nbttags = new GameObjectRegistry<NBTTag>();
	public final GameObjectRegistry<Particle> particles = new GameObjectRegistry<Particle>();
	public final GameObjectRegistry<Sound> sounds = new GameObjectRegistry<Sound>();

	public VersionRegistry(Version version)
	{
		this.version = version;
	}

}
