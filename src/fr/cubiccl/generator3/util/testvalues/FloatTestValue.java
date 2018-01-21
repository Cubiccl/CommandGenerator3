package fr.cubiccl.generator3.util.testvalues;

public class FloatTestValue extends TestValue
{

	private float value = 0;

	public void setValue(float value)
	{
		this.value = value;
		this.setUsed(true);
	}

	public float value()
	{
		return this.value;
	}

}
