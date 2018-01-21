package fr.cubiccl.generator3.util.testvalues;

public class IntTestValue extends TestValue
{

	private int value = 0;

	public void setValue(int value)
	{
		this.value = value;
		this.setUsed(true);
	}

	public int value()
	{
		return this.value;
	}

}
