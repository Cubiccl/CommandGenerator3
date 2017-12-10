package fr.cubiccl.generator3.game.object.type;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Predicate;

import fr.cubiccl.generator3.util.*;

public class Block extends GameObjectType
{

	/** The possible {@link BlockState Block states} for this Block. */
	public HashMap<String, BlockState> blockStates;
	/** Numerical ID of this Block. */
	public final int idInt;
	/** Text ID of this Block. */
	public final String idString;
	/** The maximum damage value for this Block. */
	public int maxDamage;
	/** Defines how to handle language and texture.<br />
	 * If <code>0</code>, default.<br />
	 * If <code>-1</code>, texture is the same for any damage.<br />
	 * If positive, texture index is the damage modulo this texture type.<br />
	 * If negative, texture index is the damage divided by this texture type. */
	public int textureType;
	/** Damage values that would be created with the {@link Block#states Block states} but are not used. */
	public HashSet<Integer> unusedDamage;

	public Block(int idInt, String idString)
	{
		this(idInt, idString, 0);
	}

	public Block(int idInt, String idString, int maxDamage)
	{
		super(Persistance.selectedVersion);
		this.idString = idString == null ? null : "minecraft:" + idString;
		this.idInt = idInt;
		this.textureType = 0;
		this.maxDamage = maxDamage;
		this.blockStates = new HashMap<String, BlockState>();
		this.unusedDamage = new HashSet<Integer>();
	}

	/** Adds a State to this Block.
	 * 
	 * @param state - The Block state to add. */
	public void addBlockState(BlockState state)
	{
		this.blockStates.put(state.id, state);
		this.updateDamageValues();
	}

	/** Parses the input Block states to determine the corresponding damage value.
	 * 
	 * @param state - The states to apply.
	 * @return The resulting damage value.
	 * @throws CommandGenerationException - If State parsing fails. */
	public int damageFromState(String state) throws Exception
	{
		HashMap<String, String> parsed = BlockState.parseState(state);
		int damage = 0;
		for (String id : parsed.keySet())
			if (this.blockStates.keySet().contains(id) && this.blockStates.get(id).hasValue(parsed.get(id))) damage += this.blockStates.get(id).damageForValue(
					parsed.get(id));
		if (this.isDamageValid(damage)) return damage;
		return 0;
	}

	/** Finds the Block states described by the input damage value.
	 * 
	 * @param damage - A damage value.
	 * @return A Hashmap containing a value for each used Block state. */
	public HashMap<String, String> findStatesFromDamage(int damage)
	{
		ArrayList<BlockState> s = new ArrayList<BlockState>();
		s.addAll(this.blockStates.values());
		s.removeIf(new Predicate<BlockState>()
		{

			@Override
			public boolean test(BlockState t)
			{
				return !t.isDamageCustom() && t.damageValue == -1;
			}
		});
		s.sort(Comparator.naturalOrder());
		HashMap<String, String> states = new HashMap<String, String>();
		for (int i = s.size() - 1; i >= 0; --i)
		{
			ArrayList<Integer> d = s.get(i).damageValues();
			for (int j = d.size() - 1; j >= 0; --j)
				if (damage >= d.get(j))
				{
					damage -= d.get(j);
					states.put(s.get(i).id, s.get(i).valueForDamage(d.get(j)));
					break;
				}
		}

		return states;
	}

	@Override
	public String id()
	{
		return this.idString;
	}

	/** @param damage - The damage value to test.
	 * @return <code>true</code> if the input damage value is valid for this Block. */
	public boolean isDamageValid(int damage)
	{
		if (this.unusedDamage.contains(damage)) return false;
		return damage >= 0 && damage <= this.maxDamage;
	}

	/** @return <code>true</code> if this Block's texture is unique. */
	private boolean isTextureUnique()
	{
		if (this.maxDamage == 0 || this.textureType == -1) return true;
		if (this.textureType < -1)
		{
			for (int i = 0; i <= this.maxDamage; ++i)
				if (!this.unusedDamage.contains(i) && i >= -this.textureType) return false;
			return true;
		}
		return false;
	}

	@Override
	public Text name()
	{
		return this.nameMain();
	}

	/** @param damage - A damage value.
	 * @return The name of this Block for the input damage value. */
	public Text name(int damage)
	{
		if (this.maxDamage == 0) return new Text("block." + this.idString);
		return new Text("block." + this.idString + "." + damage);
	}

	/** @return The name of this Block's group (no damage value). */
	public Text nameMain()
	{
		String nameID = this.idString;
		if (Lang.keyExists("block." + nameID)) return new Text("block." + nameID);
		if (Lang.keyExists("item." + nameID)) return new Text("item." + nameID);
		Logger.log("Couldn't find translation for : block." + nameID);
		return new Text("block." + nameID);
	}

	public Block setTextureType(int textureType)
	{
		this.textureType = textureType;
		return this;
	}

	/** @param damage - A damage value.
	 * @return The name of this Block for the input damage value. */
	public BufferedImage texture(int damage)
	{
		if (this.isTextureUnique()) return Textures.getTexture("block." + this.idString);
		if (this.textureType == 0) return Textures.getTexture("block." + this.idString + "_" + damage);
		if (this.textureType < -1) return Textures.getTexture("block." + this.idString + "_" + damage / -this.textureType);
		return Textures.getTexture("block." + this.idString + "_" + damage % this.textureType);
	}

	/** @return The texture to use to represent this Block's group (no damage value). */
	public BufferedImage textureMain()
	{
		return this.texture(0);
	}

	/** Reloads damage values to match this Block's states. */
	private void updateDamageValues()
	{
		ArrayList<Integer> damage = new ArrayList<Integer>(), previousDamage = new ArrayList<Integer>(), temp = new ArrayList<Integer>();
		ArrayList<BlockState> s = new ArrayList<BlockState>();
		s.addAll(this.blockStates.values());
		s.sort(Comparator.naturalOrder());
		damage.add(0);

		for (BlockState state : s)
		{
			// Store previous damage values in temporary array
			previousDamage.clear();
			previousDamage.addAll(damage);
			damage.clear();

			// Get damage values
			temp.clear();
			temp.addAll(state.damageValues());

			for (Integer previous : previousDamage)
				for (Integer d : temp)
					damage.add(d + previous);
		}

		damage.sort(Comparator.naturalOrder());
		if (!this.isDamageValid(0)) damage.remove(new Integer(0));
		damage.removeAll(this.unusedDamage);

		int[] d = new int[damage.size()];
		for (int i = 0; i < d.length; ++i)
			d[i] = damage.get(i);

		int current = 0;
		this.unusedDamage.clear();
		for (int v : d)
		{
			while (current < v)
			{
				this.unusedDamage.add(current);
				++current;
			}
			this.maxDamage = current;
			++current;
		}
	}

}
