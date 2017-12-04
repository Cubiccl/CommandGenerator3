package fr.cubiccl.generator3.util;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** Some text to display to the user. */
public class Text
{

	/** Replaces the content of a translation. */
	public static class Replacement
	{
		/** The pattern to locate. */
		public final String pattern;
		/** What to replace with. */
		public final Text replacement;

		public Replacement(String pattern, String replacement)
		{
			super();
			this.pattern = pattern;
			this.replacement = new Text(replacement, false);
		}

		public Replacement(String pattern, Text replacement)
		{
			super();
			this.pattern = pattern;
			this.replacement = replacement;
		}

		/** Applies this Replacement.
		 * 
		 * @param text - The text to apply to.
		 * @return The resulting text. */
		public String apply(String text)
		{
			return text.replaceAll(this.pattern, this.replacement.toString());
		}

	}

	/** <code>true</code> if this Text should be translated. */
	public boolean doTranslate;
	/** The ID to translate. Can also be the actual text if {@link Text#doTranslate} is false. */
	public String id;
	/** The Replacements to apply to the translated text, in order. */
	private ArrayList<Replacement> replacements;
	public final StringProperty value = new SimpleStringProperty(null);

	public Text(String id, boolean doTranslate, Replacement... replacements)
	{
		this.id = id;
		this.doTranslate = doTranslate;
		this.replacements = new ArrayList<Replacement>();
		for (Replacement replacement : replacements)
			this.addReplacement(replacement);
		Lang.register(this);
	}

	public Text(String id, Replacement... replacements)
	{
		this(id, true, replacements);
	}

	/** Adds a Replacement. */
	public Text addReplacement(Replacement replacement)
	{
		this.replacements.add(replacement);
		return this;
	}

	/** Adds a Replacement. */
	public Text addReplacement(String pattern, String replacement)
	{
		this.addReplacement(new Replacement(pattern, replacement));
		return this;
	}

	/** Adds a Replacement. */
	public Text addReplacement(String pattern, Text replacement)
	{
		this.addReplacement(new Replacement(pattern, replacement));
		return this;
	}

	/** @param pattern - The pattern to locate.
	 * @return <code>true</code> if there is already a Replacement looking for the input <code>pattern</code>. */
	public boolean hasReplacement(String pattern)
	{
		for (Replacement replacement : this.replacements)
			if (replacement.pattern.equals(pattern)) return true;
		return false;
	}

	/** @return True if the translation files contain this text's ID and if each of the Replacements are translated as well. */
	public boolean isTranslated()
	{
		for (Replacement r : this.replacements)
			if (!r.replacement.isTranslated()) return false;
		return (!this.doTranslate || Lang.keyExists(this.id));
	}

	/** Removes all Replacements that replace the input <code>pattern</code>.
	 * 
	 * @param pattern - The pattern to locate. */
	public void removeReplacements(String pattern)
	{
		ArrayList<Replacement> r = new ArrayList<Replacement>();
		for (Replacement replacement : this.replacements)
			if (replacement.pattern.equals(pattern)) r.add(replacement);

		for (Replacement replacement : r)
			this.replacements.remove(replacement);
	}

	@Override
	public String toString()
	{
		if (this.value.get() == null) this.translate();
		return this.value.get();
	}

	void translate()
	{
		if (this.doTranslate)
		{
			String output = Lang.translate(this.id);
			for (Replacement replacement : this.replacements)
				output = replacement.apply(output);
			this.value.setValue(output);
		} else this.value.setValue(this.id);
	}
}
