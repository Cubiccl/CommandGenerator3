package fr.cubiccl.generator3.game.object.global;

import javafx.scene.image.Image;
import fr.cubiccl.generator3.game.object.type.Effect;
import fr.cubiccl.generator3.util.Settings.Version;
import fr.cubiccl.generator3.util.Textures;

public class GlobalEffect extends GlobalObject implements TexturedObject
{
	public GlobalEffect(String id, int order)
	{
		super("effect." + id, order);
	}

	@Override
	protected String prefix()
	{
		return "effect";
	}

	@Override
	public Image texture()
	{
		return Textures.getTexture(this.id);
	}

	@Override
	public Effect value(Version version)
	{
		return VersionTranslator.translator(version).effects.get(this);
	}

}
