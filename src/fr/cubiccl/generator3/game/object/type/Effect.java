package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.game.object.global.GlobalEffect;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
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

	public GlobalEffect globalValue()
	{
		return VersionTranslator.translator(this.version).effects.inverse().get(this);
	}

}
