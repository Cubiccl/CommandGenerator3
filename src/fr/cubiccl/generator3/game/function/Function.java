package fr.cubiccl.generator3.game.function;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Function
{

	public final StringProperty name;

	public Function(String name)
	{
		this.name = new SimpleStringProperty(name);
	}

	public String name()
	{
		return this.name.get();
	}

}
