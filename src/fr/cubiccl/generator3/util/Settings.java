package fr.cubiccl.generator3.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Properties;

/** Settings. */
public class Settings
{
	/** The available Languages. */
	public static enum Language
	{
		ENGLISH("en", "English"),
		FRENCH("fr", "Français");

		/** @return The Language matching the input <code>id</code>. */
		public static Language get(String id)
		{
			for (Language l : Language.values())
				if (l.id.equals(id)) return l;
			return get(Settings.getDefault(LANG));
		}

		/** This Language's id. */
		public final String id;
		/** This Language's name. */
		public final String name;

		private Language(String id, String name)
		{
			this.id = id;
			this.name = name;
		}

		@Override
		public String toString()
		{
			return this.name;
		}

	}

	/** The available Minecraft Versions. */
	public static enum Version
	{

		v111("1.11.2", "1.11", 0),
		v112("1.12", "1.12", 1);

		private static final Comparator<Version> versionComparator = new Comparator<Version>()
		{
			@Override
			public int compare(Version o1, Version o2)
			{
				return o1.compare(o2);
			}
		};

		public static Version earliest()
		{
			ArrayList<Version> vs = new ArrayList<Version>();
			for (Version version : values())
				vs.add(version);
			vs.sort(versionComparator);
			return vs.get(0);
		}

		public static Version get(String id)
		{
			for (Version v : Version.values())
				if (v.id.equals(id)) return v;
			return v112;
		}

		/** @return An array containing all Versions. */
		public static Version[] getVersions()
		{
			ArrayList<Version> vs = new ArrayList<Version>();
			for (Version version : values())
				vs.add(version);
			vs.sort(versionComparator);
			return vs.toArray(new Version[vs.size()]);
		}

		public static Version latest()
		{
			ArrayList<Version> vs = new ArrayList<Version>();
			for (Version version : values())
				vs.add(version);
			vs.sort(versionComparator);
			return vs.get(vs.size() - 1);
		}

		/** This Version's ID. */
		public final String id;
		/** This Version's name. */
		public final String name;

		/** The position of the Version. */
		public final int order;

		private Version(String name, String id, int order)
		{
			this.id = id;
			this.name = name;
			this.order = order;
		}

		public int compare(Version anotherVersion)
		{
			return Integer.compare(this.order, anotherVersion.order);
		}

		/** @return <code>true</code> if this Version is after or equal to another Version. */
		public boolean isAfter(Version anotherVersion)
		{
			return this.compare(anotherVersion) >= 0;
		}

		/** @return <code>true</code> if this Version is before or equal to another Version. */
		public boolean isBefore(Version anotherVersion)
		{
			return this.compare(anotherVersion) <= 0;
		}
	}

	/** The Generator's version. */
	public static final String GENERATOR_VERSION = "2.6.3.2";
	/** Setting IDs. */
	public static final String LANG = "lang", SORT_TYPE = "sort", INDENTATION = "indentation", LAST_FOLDER = "folder", MINECRAFT_LOCATION = "mc_location";
	/** The selected Language. */
	private static Language language;
	/** Stores the Settings. */
	private static Properties settings = new Properties();
	/** <code>true</code> if the Generator is in debug mode. */
	public static boolean testMode = false;

	/** @param id - The Setting's ID.
	 * @return The default value for the input Setting. */
	private static String getDefault(String id)
	{
		switch (id)
		{
			case LANG:
				return Language.ENGLISH.id;

			case LAST_FOLDER:
				return "";

			case INDENTATION:
				return "true";

			case MINECRAFT_LOCATION:
				String workingDirectory;
				String OS = (System.getProperty("os.name")).toUpperCase();
				if (OS.contains("WIN")) workingDirectory = System.getenv("AppData");
				else
				{
					workingDirectory = System.getProperty("user.home");
					workingDirectory += "\\Library\\Application Support";
				}
				return workingDirectory + "\\.minecraft";

			default:
				return null;
		}
	}

	/** @param id - The Setting's ID.
	 * @return The value of the input Setting. */
	public static String getSetting(String id)
	{
		if (!settings.containsKey(id)) setSetting(id, getDefault(id));
		return (String) settings.get(id);
	}

	/** @return The selected {@link Language}. */
	public static Language language()
	{
		if (language == null) language = Language.get(getSetting(LANG));
		return language;
	}

	/** Loads the Settings by reading the settings file. */
	public static void loadSettings()
	{
		try
		{
			settings.load(new FileInputStream(new File("settings.txt")));
		} catch (IOException e)
		{
			Logger.log("Settings file not found, creating default.");
			// e.printStackTrace();
		}
		String lang = getDefault(LANG);
		if (!settings.containsKey(LANG)) settings.put(LANG, lang);
		setSetting(LANG, settings.getProperty(LANG));
	}

	/** Saves the Settings to the file. */
	public static void save()
	{
		try
		{
			settings.store(new FileOutputStream(new File("settings.txt")), null);
			Logger.log("Preferences saved.");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/** Changes the selected Language.
	 * 
	 * @param newLanguage - The new Language. */
	private static void setLanguage(Language newLanguage)
	{
		language = newLanguage;
		Lang.updateLang();
	}

	/** Changes the value of a setting.
	 * 
	 * @param id - The Setting's ID.
	 * @param value - The value of the Setting. */
	public static void setSetting(String id, String value)
	{
		settings.put(id, value);
		if (id.equals(LANG)) setLanguage(Language.get(value));
	}

	private Settings()
	{}

}
