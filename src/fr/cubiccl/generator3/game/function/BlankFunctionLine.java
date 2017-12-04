package fr.cubiccl.generator3.game.function;

public class BlankFunctionLine extends FunctionLine
{

	public BlankFunctionLine()
	{
		super(BLANK);
	}

	@Override
	public String value()
	{
		return "";
	}

}
