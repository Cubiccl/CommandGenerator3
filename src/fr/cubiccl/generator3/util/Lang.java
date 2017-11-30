package fr.cubiccl.generator3.util;

import java.util.ArrayList;
import java.util.HashMap;

import fr.cubiccl.generator3.util.Settings.Language;

/** Manages translations. */
public class Lang
{

	/** Contains all translations for the current language. */
	private static final HashMap<String, String> dictionnary = new HashMap<String, String>();
	/** Contains all translations for the default language (English). */
	private static final HashMap<String, String> english = new HashMap<String, String>();
	/** Contains the mapping changes. */
	private static final HashMap<String, String> remapping = new HashMap<String, String>();
	/** Contains all untranslated IDs. */
	public static ArrayList<String> untranslated = new ArrayList<String>();

	/** When loading a language, checks if every ID is translated (uses English as reference). */
	public static void checkTranslations()
	{
		for (String id : FileUtils.readFileAsArray("untranslated.txt"))
			if (!english.containsKey(id) && !untranslated.contains(id)) untranslated.add(id);
	}

	/** Translates the input ID directly. Does not check for remapping or grammar.
	 * 
	 * @param textID - The ID to translate.
	 * @return The translation of the input ID. Returns the ID itself if it's not translated. */
	private static String doTranslate(String textID)
	{
		if (Settings.language() != Language.ENGLISH && dictionnary.containsKey(textID)) return dictionnary.get(textID);
		if (english.containsKey(textID)) return english.get(textID);
		return textID;
	}

	/** @return True if the input <code>textID</code> exists in the language files. */
	public static boolean keyExists(String textID)
	{
		return english.containsKey(textID.replaceAll("minecraft:", "")) || dictionnary.containsKey(textID.replaceAll("minecraft:", ""));
	}

	/** Loads the English translations. */
	private static void loadEnglish()
	{
		english.clear();
		String[] translations = FileUtils.readFileAsArray("lang/" + Language.ENGLISH.id + ".txt");
		for (String translation : translations)
			if (translation.contains("=")) english.put(translation.substring(0, translation.indexOf('=')), translation.substring(translation.indexOf('=') + 1));
	}

	/** Loads the remapping. */
	private static void loadRemapping()
	{
		remapping.clear();
		String[] translations = FileUtils.readFileAsArray("lang/remapping.txt");
		for (String path : translations)
		{
			String[] data = path.split("=");
			for (int i = 0; i < data.length - 1; ++i)
				remapping.put(data[i], data[data.length - 1]);
		}
	}

	/** Translates a text ID. Checks for remapping and logs if it's not translated.
	 * 
	 * @param textID - A text to translate.
	 * @return The translation if found. If not, returns <code>textID</code>. */
	public static String translate(String textID)
	{
		textID = textID.replaceAll("minecraft:", "");
		while (remapping.containsKey(textID))
			textID = remapping.get(textID);
		if (!keyExists(textID) && !untranslated.contains(textID))
		{
			untranslated.add(textID);
			Logger.log("Couldn't find translation for : " + textID);
			// new Exception().printStackTrace();
		}
		return doTranslate(textID);
	}

	/** Updates the languages. Checks for the current languages and reads the files. */
	public static void updateLang()
	{
		if (english.size() == 0) loadEnglish();
		if (remapping.size() == 0) loadRemapping();
		dictionnary.clear();

		if (Settings.language() == Language.ENGLISH) return;
		String[] translations = FileUtils.readFileAsArray("lang/" + Settings.language().id + ".txt");
		for (String translation : translations)
		{
			if (translation.contains("=")) dictionnary.put(translation.substring(0, translation.indexOf('=')),
					translation.substring(translation.indexOf('=') + 1));
		}

		for (String textID : english.keySet())
			if (!dictionnary.containsKey(textID))
			{
				Logger.log("Not translated in " + Settings.language().name + " : " + textID);
				if (Settings.testMode && !untranslated.contains(textID)) untranslated.add(textID);
			}
	}

	private Lang()
	{}
}
