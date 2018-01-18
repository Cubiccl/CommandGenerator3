package fr.cubiccl.generator3.util;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject.Member;
import com.eclipsesource.json.JsonValue;

public class Utils
{

	/** Returns an array containing all values between 0 and max, included. */
	public static int[] generateArray(int max)
	{
		return generateArray(0, max);
	}

	/** Returns an array containing all values between min and max, included. */
	public static int[] generateArray(int min, int max)
	{
		int[] array = new int[max - min + 1];
		for (int i = 0; i < array.length; ++i)
			array[i] = i + min;
		return array;
	}

	public static Member jsonMember(String name, JsonValue value)
	{
		return Json.object().add(name, value).iterator().next();
	}

}
