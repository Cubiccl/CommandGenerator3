package fr.cubiccl.generator3.util;

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

}
