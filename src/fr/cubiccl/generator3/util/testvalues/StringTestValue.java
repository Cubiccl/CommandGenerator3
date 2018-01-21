package fr.cubiccl.generator3.util.testvalues;

public class StringTestValue extends TestValue
{

	private String value = null;

	public void setValue(String value)
	{
		this.value = value;
		this.setUsed(true);
	}

	public String value()
	{
		return this.value;
	}

}
