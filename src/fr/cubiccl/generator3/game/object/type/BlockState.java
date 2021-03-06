package fr.cubiccl.generator3.game.object.type;

import java.util.HashMap;

import fr.cubiccl.generator3.util.Text;

public class BlockState implements Comparable<BlockState>
{
	/** Identifiers for Block states value types.<br />
	 * <br />
	 * <table border="1">
	 * <tr>
	 * <td>ID</td>
	 * <td>Variable</td>
	 * <td>Mode</td>
	 * </tr>
	 * <tr>
	 * <td>0</td>
	 * <td>STRING</td>
	 * <td>String value</td>
	 * </tr>
	 * <tr>
	 * <td>1</td>
	 * <td>BOOLEAN</td>
	 * <td><code>"true"</code> or <code>"false"</code></td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>INTEGER</td>
	 * <td>Integer value</td>
	 * </tr>
	 * </table> */
	public static final byte STRING = 0, BOOLEAN = 1, INTEGER = 2;

	/** Parses the input Block states.
	 * 
	 * @param state - The states to apply.
	 * @return A Hashmap containing a value for each used Block state.
	 * @throws CommandGenerationException - If State parsing fails. */
	public static HashMap<String, String> parseState(String state) throws Exception
	{
		HashMap<String, String> parsed = new HashMap<String, String>();
		try
		{
			String[] parts = state.split(",");
			for (String part : parts)
				parsed.put(part.substring(0, part.indexOf('=')), part.substring(part.indexOf('=') + 1));
		} catch (Exception e)
		{
			throw new Exception(new Text("error.state.parsing").toString());
		}
		return parsed;
	}

	/** @param states - A Hashmap containing a value for each used Block state.
	 * @return The command format for the input Block states. */
	public static String toCommand(HashMap<String, String> states)
	{
		String command = "";
		for (String id : states.keySet())
		{
			command += id + "=" + states.get(id) + ",";
		}
		return command.substring(0, command.length() - 1);
	}

	/** This Block state's ID. */
	public final String id;
	/** The type of the value.
	 * 
	 * @see BlockState#STRING */
	public final byte type;
	/** The state's values. */
	public final String[] values;

	public BlockState(String id, byte type, String... values)
	{
		super();
		this.id = id;
		this.type = type;
		this.values = values;
	}

	@Override
	public int compareTo(BlockState o)
	{
		return this.id.compareTo(o.id);
	}

	/** @param value - A Block state value.
	 * @return <code>true</code> if the input value is possible for this State. */
	public boolean hasValue(String value)
	{
		for (String v : this.values)
			if (v.equals(value)) return true;
		return false;
	}
}
