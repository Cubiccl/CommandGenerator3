package fr.cubiccl.generator3.game.object.type;

import java.awt.image.BufferedImage;

import fr.cubiccl.generator3.game.object.global.GlobalItem;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.*;

public class Item extends GameObjectType
{

	/** <code>true</code> if this Item has durability. */
	public final int durability;
	/** Text ID of this Item. */
	public final String id;

	public Item(String id)
	{
		this(id, 0);
	}

	public Item(String id, int durability)
	{
		super(Persistance.selectedVersion);
		this.id = "minecraft:" + id;
		this.durability = durability;
	}

	public GlobalItem globalValue()
	{
		return VersionTranslator.translator(this.version).items.inverse().get(this);
	}

	@Override
	public String id()
	{
		return this.id;
	}

	@Override
	public Text name()
	{
		String nameID = this.id;
		if (Lang.keyExists("item." + nameID)) return new Text("item." + nameID);
		if (Lang.keyExists("block." + nameID)) return new Text("block." + nameID);
		Logger.log("Couldn't find translation for : item." + nameID);
		return new Text("item." + nameID);
	}

	/** @return The texture of this Item. */
	public BufferedImage texture()
	{
		return Textures.getTexture("item." + this.id);
	}

}
