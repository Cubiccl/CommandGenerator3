package fr.cubiccl.generator3.game.object.global;

import javafx.scene.image.Image;
import fr.cubiccl.generator3.game.object.type.Entity;
import fr.cubiccl.generator3.util.Settings.Version;
import fr.cubiccl.generator3.util.Textures;

public class GlobalEntity extends GlobalObject implements TexturedObject
{
	public GlobalEntity(String id, int order)
	{
		super("entity." + id, order);
	}

	@Override
	protected String prefix()
	{
		return "entity";
	}

	@Override
	public Image texture()
	{
		return Textures.getTexture(this.id);
	}

	@Override
	public Entity value(Version version)
	{
		return VersionTranslator.translator(version).entities.get(this);
	}

}
