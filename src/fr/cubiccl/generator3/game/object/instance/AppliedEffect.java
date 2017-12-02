package fr.cubiccl.generator3.game.object.instance;

import fr.cubiccl.generator3.game.object.type.Effect;

public class AppliedEffect implements GameObjectInstance
{

	/** The Effect duration. */
	public final int duration;
	/** The Effect type. */
	public final Effect effect;
	/** True if the Effect is Ambient. */
	public final boolean isAmbient;
	/** The Effect amplifier. */
	public final int amplifier;

	public AppliedEffect(Effect effect, int amplifier, int duration, boolean isAmbient)
	{
		this.effect = effect;
		this.amplifier = amplifier;
		this.duration = duration;
		this.isAmbient = isAmbient;
	}

}
