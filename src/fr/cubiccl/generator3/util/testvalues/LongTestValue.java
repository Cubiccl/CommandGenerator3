package fr.cubiccl.generator3.util.testvalues;

public class LongTestValue extends TestValue
{

	private long value = 0;

	public void setValue(long value)
	{
		this.value = value;
		this.setUsed(true);
	}

	public long value()
	{
		return this.value;
	}

}
