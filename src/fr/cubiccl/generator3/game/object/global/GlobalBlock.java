package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.datapack.DataPacks.Version;
import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.util.Textures;
import javafx.scene.image.Image;

public class GlobalBlock extends GlobalObject implements TexturedObject
{
	public GlobalBlock(String id, int order)
	{
		super("block." + id, order);
	}

	@Override
	protected String prefix()
	{
		return "block";
	}

	@Override
	public Image texture()
	{
		return Textures.getTexture(this.id);
	}

	@Override
	public Block value(Version version)
	{
		return VersionTranslator.translator(version).blocks.get(this);
	}

}
