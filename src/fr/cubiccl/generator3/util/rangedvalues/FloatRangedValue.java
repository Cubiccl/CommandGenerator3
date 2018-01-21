package fr.cubiccl.generator3.util.rangedvalues;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonValue;

public class FloatRangedValue extends RangedValue
{

	private float value, maxValue;

	public float getMaxValue()
	{
		return this.maxValue;
	}

	public float getValue()
	{
		return this.value;
	}

	@Override
	public boolean isRanged()
	{
		return this.value != this.maxValue;
	}

	@Override
	public FloatRangedValue readJson(JsonValue json)
	{
		if (json.isObject())
		{
			this.setValue(json.asObject().getFloat(JSON_MIN, 0));
			this.setMaxValue(json.asObject().getFloat(JSON_MAX, 0));
		} else
		{
			this.setValue(json.asFloat());
			this.setMaxValue(this.getValue());
		}
		return this;
	}

	public void setMaxValue(float maxValue)
	{
		this.maxValue = maxValue;
	}

	public void setValue(float value)
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
