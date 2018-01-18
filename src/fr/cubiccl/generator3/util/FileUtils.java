package fr.cubiccl.generator3.util;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

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

	/** Saves a single line in the input file.
	 * 
	 * @param data - The line to write.
	 * @param file - The file. */
	public static void writeToFile(String data, File file)
	{
		writeToFile(new String[]
		{ data }, file);
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
