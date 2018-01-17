package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Lang;
import fr.cubiccl.generator3.util.Persistance;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Textures;
import javafx.scene.image.Image;

public class Item extends GameObjectType
{

	/** <code>true</code> if this Item has durability. */
	public final int durability;
	public final int idInt;
	public final Image texture;

	public Item(String id, int idInt)
	{
		this(id, idInt, 0);
	}

	public Item(String id, int idInt, int durability)
	{
		super("minecraft:" + id, Persistance.selectedVersion);
		this.idInt = idInt;
		this.durability = durability;
		{
			if (Textures.exists("item." + this.idPrefixless())) this.texture = Textures.getTexture("item." + this.idPrefixless());
			else if (Textures.exists("block." + this.idPrefixless())) this.texture = Textures.getTexture("block." + this.idPrefixless());
			else this.texture = Textures.getTexture("item." + this.idPrefixless());
		}
	}

	@Override
	public int compareTo(GameObjectType o)
	{
		if (!(o instanceof Item)) return 0;
		return Integer.compare(this.idInt, ((Item) o).idInt);
	}

	@Override
	protected Text createName()
	{
		if (Lang.keyExists("item." + this.idPrefixless())) return new Text("item." + this.idPrefixless());
		if (Lang.keyExists("block." + this.idPrefixless())) return new Text("block." + this.idPrefixless());
		return new Text("item." + this.idPrefixless());
	}

	@Override
	public String describe()
	{
		return super.describe() + " (" + this.idInt + ")";
	}

	@Override
	public String type()
	{
		return "Item";
	}

}
