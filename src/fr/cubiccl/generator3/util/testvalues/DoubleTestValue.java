package fr.cubiccl.generator3.util.testvalues;

public class DoubleTestValue extends TestValue
{

	private double value = 0;

	public void setValue(double value)
	{
		this.value = value;
		this.setUsed(true);
	}

	public double value()
	{
		return this.value;
	}

}
