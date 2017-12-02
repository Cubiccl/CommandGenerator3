package fr.cubiccl.generator3.game.object.type;

import java.awt.image.BufferedImage;

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
