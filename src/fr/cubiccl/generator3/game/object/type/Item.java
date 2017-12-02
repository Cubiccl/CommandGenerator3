package fr.cubiccl.generator3.game.object.type;

import java.awt.image.BufferedImage;

import fr.cubiccl.generator3.game.object.global.GlobalItem;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.*;

public class Item extends GameObjectType
{

	/** <code>true</code> if this Item has durability. */
	public boolean hasDurability = false;
	/** Numerical ID of this Item. */
	public final int idInt;
	/** Text ID of this Item. */
	public final String idString;
	/** The maximum damage value this Item may have. */
	public int maxDamage;

	public Item(int idInt, String idString)
	{
		this(idInt, idString, 0);
	}

	public Item(int idInt, String idString, int maxDamage)
	{
		super(Persistance.selectedVersion);
		this.idString = idString == null ? null : "minecraft:" + idString;
		this.idInt = idInt;
		this.maxDamage = maxDamage;
	}

	/** @return The list of possible damage values for this Item. */
	public int[] getDamageValues()
	{
		return Utils.generateArray(this.maxDamage);
	}

	/** @return The maximum durability for this Item. */
	public int getDurability()
	{
		return this.maxDamage;
	}

	public GlobalItem globalValue()
	{
		return VersionTranslator.translator(this.version).itemGroups.inverse().get(this);
	}

	@Override
	public String id()
	{
		return this.idString;
	}

	/** @param damage - The damage value to test.
	 * @return <code>true</code> if the input damage value is valid for this Item. */
	public boolean isDamageValid(int damage)
	{
		return damage >= 0 && damage <= this.maxDamage;
	}

	/** @return <code>true</code> if this Item's texture is unique. */
	private boolean isTextureUnique()
	{
		return this.hasDurability || this.maxDamage == 0;
	}

	@Override
	public Text name()
	{
		return this.nameMain();
	}

	/** @param damage - A damage value.
	 * @return The name of this Item for the input damage value. */
	public Text name(int damage)
	{
		if (this.maxDamage == 0 || this.hasDurability) return new Text("item." + this.idString);
		return new Text("item." + this.idString + "." + damage);
	}

	/** @return The name of the general Item (no damage value) */
	public Text nameMain()
	{
		String nameID = this.idString;
		if (Lang.keyExists("item." + nameID)) return new Text("item." + nameID);
		if (Lang.keyExists("block." + nameID)) return new Text("block." + nameID);
		Logger.log("Couldn't find translation for : item." + nameID);
		return new Text("item." + nameID);
	}

	/** Sets this Item's durability.
	 * 
	 * @param durability - The durability to apply. */
	public Item setDurability(int durability)
	{
		this.hasDurability = durability != -1;
		this.maxDamage = durability;
		return this;
	}

	/** @param damage - A damage value.
	 * @return The texture of this Item for the input damage value. */
	public BufferedImage texture(int damage)
	{
		if (this.isTextureUnique()) return Textures.getTexture("item." + this.idString);
		return Textures.getTexture("item." + this.idString + "_" + damage);
	}

	/** @return The texture of the general Item (no damage value). */
	public BufferedImage textureMain()
	{
		return this.texture(0);
	}

}
