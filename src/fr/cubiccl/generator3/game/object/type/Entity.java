package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Textures;
import javafx.scene.image.Image;

public class Entity extends GameObjectType
{

	public final int order;
	public final Image texture;

	public Entity(String id, int order)
	{
		super("minecraft:" + id);
		this.order = order;
		this.texture = Textures.getTexture("entity." + this.idPrefixless());
	}

	@Override
	public int compareTo(GameObjectType o)
	{
		if (!(o instanceof Entity)) return 0;
		return Integer.compare(this.order, ((Entity) o).order);
	}
	
	@Override
	protected Text createName()
	{
		return new Text("entity." + this.idPrefixless());
	}

	@Override
	public String type()
	{
		return "Entity";
	}

}
