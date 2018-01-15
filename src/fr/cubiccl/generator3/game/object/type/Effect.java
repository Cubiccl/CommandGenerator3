package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Persistance;
import fr.cubiccl.generator3.util.Text;

public class Effect extends GameObjectType
{

	/** This Effect's numerical ID. */
	public int idInt;

	public Effect(int idNum, String idString)
	{
		super("minecraft:" + idString, Persistance.selectedVersion);
		this.idInt = idNum;
	}

	@Override
	public int compareTo(GameObjectType o)
	{
		if (!(o instanceof Effect)) return 0;
		return Integer.compare(this.idInt, ((Effect) o).idInt);
	}

	@Override
	protected Text createName()
	{
		return new Text("effect." + this.idPrefixless());
	}

	@Override
	public String describe()
	{
		return super.describe() + " (" + this.idInt + ")";
	}

	@Override
	public String type()
	{
		return "Effect";
	}

}
