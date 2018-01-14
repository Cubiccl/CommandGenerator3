package fr.cubiccl.generator3.game.object;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import fr.cubiccl.generator3.game.object.type.GameObjectType;

public final class ObjectLists implements ListChangeListener<GameObjectType>
{
	private static final ObjectLists _instance = new ObjectLists();

	public static final ObservableList<GameObjectType> exampleList = FXCollections.observableArrayList();

	public static void load()
	{
		exampleList.addListener(_instance);
	}

	private ObjectLists()
	{}

	@Override
	public void onChanged(Change<? extends GameObjectType> c)
	{
		c.getList().sort(Comparator.naturalOrder());
	}

}
