package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Persistance;

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

}
