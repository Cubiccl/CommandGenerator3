package fr.cubiccl.generator3.game.object.data;

import fr.cubiccl.generator3.game.object.GameObject;
import fr.cubiccl.generator3.util.JsonWritable;

public abstract class DataObject extends GameObject implements Comparable<DataObject>, JsonWritable<DataObject>
{

	@Override
	public int compareTo(DataObject o)
	{
		return this.idWithoutNamespace().compareTo(o.idWithoutNamespace());
	}

	public String id()
	{
		return this.getDatapack().namespace() + this.idWithoutNamespace();
	}

	public abstract String idWithoutNamespace();

}
