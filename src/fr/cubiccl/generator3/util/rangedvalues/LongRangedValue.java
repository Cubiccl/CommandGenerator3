package fr.cubiccl.generator3.util.rangedvalues;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonValue;

public class LongRangedValue extends RangedValue
{

	private long value, maxValue;

	public long getMaxValue()
	{
		return this.maxValue;
	}

	public long getValue()
	{
		return this.value;
	}

	@Override
	public boolean isRanged()
	{
		return this.value != this.maxValue;
	}

	@Override
	public LongRangedValue readJson(JsonValue json)
	{
		if (json.isObject())
		{
			this.setValue(json.asObject().getLong(JSON_MIN, 0));
			this.setMaxValue(json.asObject().getLong(JSON_MAX, 0));
		} else
		{
			this.setValue(json.asLong());
			this.setMaxValue(this.getValue());
		}
		return this;
	}

	public void setMaxValue(long maxValue)
	{
		this.maxValue = maxValue;
	}

	public void setValue(long value)
	{
		this.value = value;
	}

	@Override
	public JsonValue toJson()
	{
		if (this.isRanged()) return Json.value(this.getValue());
		return Json.object().add(JSON_MIN, this.getValue()).add(JSON_MAX, this.getMaxValue());
	}

}
