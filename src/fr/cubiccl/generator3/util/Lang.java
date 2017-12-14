package fr.cubiccl.generator3.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;

import fr.cubiccl.generator3.controller.main.MainController;
import fr.cubiccl.generator3.util.Settings.Language;

/** Manages translations. */
public class Lang
{

	/** Contains all translations for the current language. */
	private static final Properties dictionnary = new Properties();
	/** Contains all translations for the default language (English). */
	private static final Properties english = new Properties();
	private static boolean loaded = false;
	/** Contains the mapping changes. */
	private static final Properties remapping = new Properties();
	/** Contains all instances of Text. Used when changing language, to update their values. */
	private static HashSet<Text> usedTexts = new HashSet<Text>();

	/** Translates the input ID directly. Does not check for remapping or grammar.
	 * 
	 * @param textID - The ID to translate.
	 * @return The translation of the input ID. Returns the ID itself if it's not translated. */
	private static String doTranslate(String textID)
	{
		if (Settings.language() != Language.ENGLISH && dictionnary.containsKey(textID)) return (String) dictionnary.get(textID);
		if (english.containsKey(textID)) return (String) english.get(textID);
		return textID;
	}

	public static void fullReload()
	{
		loadEnglish();
		loadRemapping();
		updateLang();
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
		try
		{
			english.load(new FileInputStream(new File(Lang.class.getResource("/lang/" + Language.ENGLISH.id + ".properties").getPath())));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/** Loads the remapping. */
	private static void loadRemapping()
	{
		remapping.clear();
		try
		{
			remapping.load(new FileInputStream(new File(Lang.class.getResource("/lang/remapping.properties").getPath())));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void register(Text text)
	{
		usedTexts.add(text);
		text.translate();
	}

	/** Translates a text ID. Checks for remapping and logs if it's not translated.
	 * 
	 * @param textID - A text to translate.
	 * @return The translation if found. If not, returns <code>textID</code>. */
	public static String translate(String textID)
	{
		textID = textID.replaceAll("minecraft:", "");
		while (remapping.containsKey(textID))
			textID = (String) remapping.get(textID);
		if (!keyExists(textID) && loaded)
		{
			Logger.log("Couldn't find translation for : " + textID);
			// new Exception().printStackTrace();
		}
		return doTranslate(textID);
	}

	public static void unregister(Text text)
	{
		usedTexts.remove(text);
	}

	/** Updates the languages. Checks for the current languages and reads the files. */
	public static void updateLang()
	{
		dictionnary.clear();

		if (Settings.language() != Language.ENGLISH)
		{
			try
			{
				dictionnary.load(new FileInputStream(new File(Lang.class.getResource("/lang/" + Settings.language().id + ".properties").getPath())));
			} catch (IOException e)
			{
				Logger.log("Couldn't find language file for: " + Settings.language());
				e.printStackTrace();
			}

			for (Object textID : english.keySet())
				if (!dictionnary.containsKey(textID)) Logger.log("Not translated in " + Settings.language().name + " : " + textID);
		}

		for (Text text : usedTexts)
			text.translate();

		if (MainController.instance != null) MainController.instance.mapExplorer.refresh();
		loaded = true;

	}

	private Lang()
	{}

}
