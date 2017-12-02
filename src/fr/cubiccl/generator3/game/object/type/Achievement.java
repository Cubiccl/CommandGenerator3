package fr.cubiccl.generator3.game.object.type;

import java.awt.image.BufferedImage;

import fr.cubiccl.generator3.game.object.global.GlobalAchievement;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.Persistance;
import fr.cubiccl.generator3.util.Text;

public class Achievement extends GameObjectType
{

	/** This Achievement's ID. */
	public final String id;
	/** The Item to use for this Achievement's Texture. */
	public final Item textureItem;

	public Achievement(String id, Item item)
	{
		super(Persistance.selectedVersion);
		this.id = "minecraft:" + id;
		this.textureItem = item;
	}

	public GlobalAchievement globalValue()
	{
		return VersionTranslator.translator(this.version).achievements.inverse().get(this);
	}

	@Override
	public String id()
	{
		return this.id;
	}

	@Override
	public Text name()
	{
		return new Text("achievement." + this.id);
	}

	public BufferedImage texture()
	{
		return this.textureItem.textureMain();
	}

}
