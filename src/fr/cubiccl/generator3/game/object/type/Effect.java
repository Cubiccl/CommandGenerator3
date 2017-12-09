package fr.cubiccl.generator3.game.object.type;

import java.awt.image.BufferedImage;

import fr.cubiccl.generator3.game.object.global.GlobalEffect;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.game.object.type.v112.Effects112;
import fr.cubiccl.generator3.util.Persistance;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Textures;

public class Effect extends GameObjectType
{

	/** This Effect's numerical ID. */
	public final int idInt;
	/** This Effect's string ID. */
	public final String idString;

	public Effect(int idNum, String idString)
	{
		super(Persistance.selectedVersion);
		this.idString = "minecraft:" + idString;
		this.idInt = idNum;
		Effects112.values.add(this);
	}

	public GlobalEffect globalValue()
	{
		return VersionTranslator.translator(this.version).effects.inverse().get(this);
	}

	@Override
	public String id()
	{
		return this.idString;
	}

	@Override
	public Text name()
	{
		return new Text("effect." + this.idString);
	}

	public BufferedImage texture()
	{
		return Textures.getTexture("effect." + this.idString);
	}

}
