package fr.cubiccl.generator3.game.object;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Versions
{

	/** The available Minecraft Versions. */
	public static enum Version
	{

		v113("1.13", "1d13", 0);

		private static final Comparator<Version> versionComparator = new Comparator<Version>() {
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
			return v113;
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

	private static final HashMap<Version, VersionRegistry> registries = new HashMap<Version, VersionRegistry>();

	static void create(Version version)
	{
		registries.put(version, new VersionRegistry(version));
	}

	public static VersionRegistry current()
	{
		return registry(Version.v113);
	}

	public static final VersionRegistry registry(Version version)
	{
		return registries.get(version);
	}

}
