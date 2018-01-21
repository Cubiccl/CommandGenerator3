package fr.cubiccl.generator3.util.testvalues;

public class BooleanTestValue extends TestValue
{

	private boolean value = false;

	public void setValue(boolean value)
	{
		this.value = value;
		this.setUsed(true);
	}

	public boolean value()
	{
		return this.value;
	}

}
