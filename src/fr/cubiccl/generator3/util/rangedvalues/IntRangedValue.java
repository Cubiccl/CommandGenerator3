package fr.cubiccl.generator3.util.rangedvalues;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonValue;

public class IntRangedValue extends RangedValue
{

	private int value, maxValue;

	public int getMaxValue()
	{
		return this.maxValue;
	}

	public int getValue()
	{
		return this.value;
	}

	@Override
	public boolean isRanged()
	{
		return this.value != this.maxValue;
	}

	@Override
	public IntRangedValue readJson(JsonValue json)
	{
		if (json.isObject())
		{
			this.setValue(json.asObject().getInt(JSON_MIN, 0));
			this.setMaxValue(json.asObject().getInt(JSON_MAX, 0));
		} else
		{
			this.setValue(json.asInt());
			this.setMaxValue(this.getValue());
		}
		return this;
	}

	public void setMaxValue(int maxValue)
	{
		this.maxValue = maxValue;
	}

	public void setValue(int value)
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
