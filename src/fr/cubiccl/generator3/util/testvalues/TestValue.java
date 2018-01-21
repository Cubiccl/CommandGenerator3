package fr.cubiccl.generator3.util.testvalues;

public abstract class TestValue
{

	private boolean isSet = false;

	/** @return True if this Value is being used. */
	public boolean isSet()
	{
		return this.isSet;
	}

	public void setUsed(boolean used)
	{
		this.isSet = used;
	}

}
