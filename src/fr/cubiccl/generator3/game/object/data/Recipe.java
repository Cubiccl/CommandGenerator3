package fr.cubiccl.generator3.game.object.data;

import java.util.ArrayList;
import java.util.HashMap;

import fr.cubiccl.generator3.game.object.instance.ItemStack;
import fr.cubiccl.generator3.game.object.type.Item;

public class Recipe
{

	/** Group of this Recipe. */
	private String group;
	/** Ingredients used in this Recipe. */
	private ArrayList<Item> ingredients;
	/** True if this Recipe is shaped. */
	private boolean isShaped;
	/** For Shaped recipes, associates each key used in the pattern to an ingredient. */
	private HashMap<String, Item> keys;
	/** For Shaped recipes, the pattern to match. */
	private String[] pattern;
	/** The Item(s) given as a result of this Recipe. */
	private ItemStack result;

	public Recipe()
	{
		this.ingredients = new ArrayList<>();
		this.keys = new HashMap<>();
		this.pattern = new String[] { "" };
	}

	public void addIngredient(Item item)
	{
		this.ingredients.add(item);
	}

	public void addKey(String key, Item item)
	{
		this.keys.put(key, item);
	}

	public String getGroup()
	{
		return this.group;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Item> getIngredients()
	{
		return (ArrayList<Item>) this.ingredients.clone();
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Item> getKeys()
	{
		return (HashMap<String, Item>) this.keys.clone();
	}

	public String[] getPattern()
	{
		return this.pattern;
	}

	public ItemStack getResult()
	{
		return this.result;
	}

	public boolean isShaped()
	{
		return this.isShaped;
	}

	public void removeIngredient(Item item)
	{
		this.ingredients.remove(item);
	}

	public void removeKey(String key)
	{
		this.keys.remove(key);
	}

	public void setGroup(String group)
	{
		this.group = group;
	}

	public void setPattern(String[] pattern)
	{
		this.pattern = pattern;
	}

	public void setResult(ItemStack result)
	{
		this.result = result;
	}

	public void setShaped(boolean isShaped)
	{
		this.isShaped = isShaped;
	}

}
