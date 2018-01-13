package fr.cubiccl.generator3.game.function;

/** Represents a line in a Function. */
public abstract class FunctionLine
{
	/** Identifiers for the line type. <br />
	 * <br />
	 * <table border="1">
	 * <th>ID</th>
	 * <th>Variable name</th>
	 * <th>Description</th>
	 * <tr>
	 * <td>0</td>
	 * <td>BLANK</td>
	 * <td>A blank line.</td>
	 * </tr>
	 * <tr>
	 * <td>1</td>
	 * <td>COMMENT</td>
	 * <td>A comment, starting with #</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>COMMAND</td>
	 * <td>A command, starting with /</td>
	 * </tr>
	 * </table> */
	public static final byte BLANK = 0, COMMENT = 1, COMMAND = 2;

	/** The type of the line. See {@link FunctionLine#BLANK Function line types}. */
	public final byte type;

	public FunctionLine(byte type)
	{
		this.type = type;
	}

	public abstract String value();

}
