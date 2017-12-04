package fr.cubiccl.generator3.game.function;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CommandFunctionLine extends FunctionLine
{

	/** The comment the user put. */
	public final StringProperty command;

	public CommandFunctionLine(String command)
	{
		super(COMMAND);
		this.command = new SimpleStringProperty(command);
	}

	public String value()
	{
		return "#" + this.command.get();
	}

}
