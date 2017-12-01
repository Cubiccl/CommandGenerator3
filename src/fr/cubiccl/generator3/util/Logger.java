package fr.cubiccl.generator3.util;

import java.util.ArrayList;

public class Logger
{

	/** The output log. */
	private static final ArrayList<String> log = new ArrayList<String>();

	/** Logs a line of text. Prints it in the console and adds it to the log.
	 * 
	 * @param text - The text to log. */
	public static void log(String text)
	{
		System.out.println(text);
		log.add(text);
	}

	public static void save()
	{
		FileUtils.writeToFile("log.txt", log.toArray(new String[log.size()]));
	}

}
