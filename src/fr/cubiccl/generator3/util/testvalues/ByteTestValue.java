package fr.cubiccl.generator3.util.testvalues;

public class ByteTestValue extends TestValue
{

	private byte value = 0;

	public void setValue(byte value)
	{
		this.value = value;
		this.setUsed(true);
	}

	public byte value()
	{
		return this.value;
	}

}
