package fr.cubiccl.generator3.game.object.type;

import java.awt.image.BufferedImage;

import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Textures;

public class EffectType extends GameObjectType
{

	/** This Effect's numerical ID. */
	public final int idInt;
	/** This Effect's string ID. */
	public final String idString;

	public EffectType(int idNum, String idString)
	{
		this.idString = "minecraft:" + idString;
		this.idInt = idNum;
	}

	@Override
	public String id()
	{
		return this.idString;
	}

	public Text name()
	{
		return new Text("effect." + this.idString);
	}

	public BufferedImage texture()
	{
		return Textures.getTexture("effect." + this.idString);
	}

}
