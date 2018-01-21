package fr.cubiccl.generator3.util.rangedvalues;

import fr.cubiccl.generator3.util.JsonWritable;

public abstract class RangedValue implements JsonWritable<RangedValue>
{
	public static final String JSON_MIN = "min", JSON_MAX = "max";

	public abstract boolean isRanged();

}
