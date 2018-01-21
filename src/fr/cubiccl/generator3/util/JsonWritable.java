package fr.cubiccl.generator3.util;

import com.eclipsesource.json.JsonValue;

public interface JsonWritable<T>
{

	public abstract T readJson(JsonValue json);

	public abstract JsonValue toJson();

}
