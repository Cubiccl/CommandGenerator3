package fr.cubiccl.generator3.util.testvalues;

public class ShortTestValue extends TestValue
{

	private short value = 0;

	public void setValue(short value)
	{
		this.value = value;
		this.setUsed(true);
	}

	public short value()
	{
		return this.value;
	}

}
