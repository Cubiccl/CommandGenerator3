package fr.cubiccl.generator3.game.object.type;

import java.awt.image.BufferedImage;

import fr.cubiccl.generator3.game.object.global.GlobalEntity;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.Persistance;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Textures;

public class Entity extends GameObjectType
{

	/** This Entity's ID. */
	public final String id;

	public Entity(String id)
	{
		super(Persistance.selectedVersion);
		this.id = "minecraft:" + id;
	}

	public GlobalEntity globalValue()
	{
		return VersionTranslator.translator(this.version).entities.inverse().get(this);
	}

	@Override
	public String id()
	{
		return this.id;
	}

	@Override
	public Text name()
	{
		return new Text("entity." + this.id);
	}

	public BufferedImage texture()
	{
		return Textures.getTexture("entity." + this.id);
	}

}
