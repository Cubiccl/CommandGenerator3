package fr.cubiccl.generator3.game.function;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CommentFunctionLine extends FunctionLine
{

	/** The comment the user put. */
	public final StringProperty comment;

	public CommentFunctionLine(String comment)
	{
		super(COMMENT);
		this.comment = new SimpleStringProperty(comment);
	}

	public String value()
	{
		return "#" + this.comment.get();
	}

}
