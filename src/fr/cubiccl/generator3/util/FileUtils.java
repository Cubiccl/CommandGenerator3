package fr.cubiccl.generator3.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Comparator;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonValue;

import fr.cubiccl.generator3.game.datapack.DataObjectLoader;
import javafx.scene.image.Image;

public class FileUtils
{

	/** Deletes the input file.
	 * 
	 * @param path - Path to the file to delete. */
	public static void delete(String path)
	{
		File f = new File(path);
		if (f.exists()) f.delete();
	}

	/** Downloads a single file.
	 * 
	 * @param url - The URL to the file.
	 * @param destination - The path to the destination location.
	 * @return True if the download was successful. */
	public static boolean download(String url, String destination)
	{
		try
		{
			URL download = new URL(url);
			ReadableByteChannel rbc = Channels.newChannel(download.openStream());
			FileOutputStream fileOut = new FileOutputStream(destination);
			fileOut.getChannel().transferFrom(rbc, 0, 1 << 24);
			fileOut.flush();
			fileOut.close();
			rbc.close();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/** @param path - The path to the file.
	 * @return <code>true</code> if the input file exists. */
	public static boolean fileExists(String path)
	{
		File file = new File(path);
		return file != null && file.exists();
	}

	/** @param file - The file.
	 * @param extension - The extension of the file.
	 * @return The name of the input File, without the extension. */
	public static String fileName(File file, String extension)
	{
		return file.getName().substring(0, file.getName().length() - extension.length());
	}

	public static ArrayList<String> getFiles(String path)
	{
		ArrayList<String> filenames = new ArrayList<>();
		File f = new File(path);
		if (f.isDirectory()) for (String fn : f.list())
			filenames.add(fn);
		return filenames;
	}

	public static ArrayList<String> getResourceFiles(String path)
	{
		ArrayList<String> filenames = new ArrayList<>();

		try (InputStream in = DataObjectLoader.class.getResourceAsStream(path); BufferedReader br = new BufferedReader(new InputStreamReader(in)))
		{
			String resource;
			while ((resource = br.readLine()) != null)
				filenames.add(resource);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return filenames;
	}

	public static ArrayList<String> getResourceSubFiles(String path)
	{
		return getResourceSubFiles(path, "");
	}

	public static ArrayList<String> getResourceSubFiles(String path, String parents)
	{
		ArrayList<String> filenames = new ArrayList<>();

		try
		{
			InputStream in = DataObjectLoader.class.getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String resource;
			while ((resource = br.readLine()) != null)
				if (resource.contains(".")) filenames.add(parents + resource);
				else filenames.addAll(getResourceSubFiles(path + "/" + resource, parents + resource + "/"));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		filenames.sort(Comparator.naturalOrder());

		return filenames;
	}

	public static ArrayList<String> getSubFiles(String path)
	{
		ArrayList<String> files = getSubFiles(path, "");
		files.sort(Comparator.naturalOrder());
		return files;
	}

	private static ArrayList<String> getSubFiles(String path, String parents)
	{
		ArrayList<String> filenames = new ArrayList<>();
		File f = new File(path);
		if (f.isDirectory()) for (String fn : f.list())
		{
			File d = new File(fn);
			if (d.isDirectory()) filenames.addAll(getSubFiles(path + "/" + fn, parents + fn + "/"));
			else filenames.add(parents + fn);
		}
		return filenames;
	}

	/** Reads the input file.
	 * 
	 * @param path - The path to the file.
	 * @return A String containing the file content. */
	public static String readFile(String path)
	{
		StringBuilder data = new StringBuilder("");
		URL url = FileUtils.class.getResource(path);
		if (url != null)
		{
			File f = new File(url.getPath());
			if (!f.exists()) return data.toString();
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line;
				while ((line = br.readLine()) != null)
					if (!line.equals("") && !line.startsWith("//")) data.append(line + "\n");
				br.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} else Logger.log("File not found : " + path);

		return data.toString();
	}

	/** Reads the input file.
	 * 
	 * @param path - The path to the file.
	 * @return A String array containing each line of the file content. */
	public static ArrayList<String> readFileAsArray(String path)
	{
		ArrayList<String> lines = new ArrayList<String>();
		URL url = FileUtils.class.getResource(path);
		if (url != null)
		{
			File f = new File(url.getPath());
			if (!f.exists()) return lines;
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line;
				while ((line = br.readLine()) != null)
					if (!line.equals("") && !line.startsWith("//")) lines.add(line);
				br.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} else Logger.log("File not found : " + path);

		return lines;
	}

	/** Reads the input file.
	 * 
	 * @param path - The path to the file.
	 * @return The read Image. */
	public static Image readImage(String path)
	{
		if (path != null) try
		{
			return new Image(FileUtils.class.getResourceAsStream(path + ".png"));
		} catch (Exception e)
		{
			Logger.log("Couldn't find Image: " + path);
			// e.printStackTrace();
			return null;
		}
		return null;
	}

	public static JsonValue readJsonFile(String path)
	{
		String data = readFile(path);
		return Json.parse(data);
	}

	/** Saves a single line in the input file.
	 * 
	 * @param data - The line to write.
	 * @param file - The file. */
	public static void writeToFile(String data, File file)
	{
		writeToFile(new String[] { data }, file);
	}

	/** @param path - Path to the File
	 * @param data - Each line to write. */
	public static void writeToFile(String path, String... data)
	{
		writeToFile(data, new File(path));
	}

	/** Saves data in the input file.
	 * 
	 * @param data - The lines to write.
	 * @param file - The file. */
	public static void writeToFile(String[] data, File file)
	{
		if (file.exists()) file.delete();
		try
		{
			PrintWriter bw = new PrintWriter(new FileWriter(file));
			for (String line : data)
				bw.println(line);
			bw.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private FileUtils()
	{}

}
