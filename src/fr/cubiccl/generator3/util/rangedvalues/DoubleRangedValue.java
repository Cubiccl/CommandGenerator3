package fr.cubiccl.generator3.util.rangedvalues;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonValue;

public class DoubleRangedValue extends RangedValue
{

	private double value, maxValue;

	public double getMaxValue()
	{
		return this.maxValue;
	}

	public double getValue()
	{
		return this.value;
	}

	@Override
	public boolean isRanged()
	{
		return this.value != this.maxValue;
	}

	@Override
	public DoubleRangedValue readJson(JsonValue json)
	{
		if (json.isObject())
		{
			this.setValue(json.asObject().getDouble(JSON_MIN, 0));
			this.setMaxValue(json.asObject().getDouble(JSON_MAX, 0));
		} else
		{
			this.setValue(json.asDouble());
			this.setMaxValue(this.getValue());
		}
		return this;
	}

	public void setMaxValue(double maxValue)
	{
		this.maxValue = maxValue;
	}

	public void setValue(double value)
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
