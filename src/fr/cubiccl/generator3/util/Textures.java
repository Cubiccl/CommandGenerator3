package fr.cubiccl.generator3.util;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.Image;

/** Manages Images. */
public class Textures
{
	/** Maps texture IDs to file paths. */
	private static final HashMap<String, String> paths = new HashMap<String, String>();
	/** Maps file paths to the loaded Buffered Images. */
	private static final HashMap<String, Image> textures = new HashMap<String, Image>();

	/** Reloads the textures and remaps the IDs to the paths. */
	public static void createTextures()
	{
		paths.clear();
		textures.clear();
		ArrayList<String> pathArray = FileUtils.readFileAsArray("/textures/texture-remapping.txt");
		for (String path : pathArray)
		{
			String[] data = path.split("=");
			for (int i = 0; i < data.length - 1; ++i)
				paths.put(data[i], "/textures/" + data[data.length - 1]);
		}
	}

	/** @param textureID - The Identifier of the Texture.
	 * @return The corresponding Image. Returns null if <code>textureID</code> is not recognized or the image File can't be found. */
	public static Image getTexture(String textureID)
	{
		String path = paths.get(textureID);

		if (path == null)
		{
			path = locateTexture(textureID);
			if (path == null) return null;
			paths.put(textureID, path);
		}

		if (!textures.containsKey(path))
		{
			loadTexture(path);
			// if (textures.get(path) == null) Logger.log("Couldn't find texture: " + textureID);
		}

		return textures.get(path);
	}

	/** Loads an Image.
	 * 
	 * @param path - The path to the File containing the Image. */
	private static void loadTexture(String path)
	{
		textures.put(path, FileUtils.readImage(path));
	}

	/** Locates the Image with the input ID.
	 * 
	 * @param textureID - The ID of the Image.
	 * @return The path to the Image File. */
	private static String locateTexture(String textureID)
	{
		String defaultPath = "/textures/" + textureID.replaceAll("\\.", "/");
		if (!textureID.startsWith("item.") || Textures.class.getResource(defaultPath + ".png") != null) return defaultPath;
		return "/textures/block/" + textureID.replaceAll("\\.", "/").substring("item/".length());
	}

	private Textures()
	{}

	/** @param textureID - The ID of the Image.
	 * @return True if the texture with the input ID exists. */
	public static boolean exists(String textureID)
	{
		return getTexture(textureID) != null;
	}

}
