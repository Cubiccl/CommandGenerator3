package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.game.object.global.GlobalAttribute;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.Persistance;
import fr.cubiccl.generator3.util.Text;

public class Attribute extends GameObjectType
{

	/** This Attribute's ID. */
	public final String id;

	public Attribute(String id)
	{
		super(Persistance.selectedVersion);
		this.id = id;
	}

	public GlobalAttribute globalValue()
	{
		return VersionTranslator.translator(this.version).attributes.inverse().get(this);
	}

	@Override
	public String id()
	{
		return this.id;
	}

	@Override
	public Text name()
	{
		return new Text("attribute." + this.id);
	}

}
