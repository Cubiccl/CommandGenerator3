package fr.cubiccl.generator3.game.object;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import fr.cubiccl.generator3.game.object.global.GlobalObject;

public final class ObjectLists implements ListChangeListener<GlobalObject>
{
	private static final ObjectLists _instance = new ObjectLists();

	public static final ObservableList<GlobalObject> exampleList = FXCollections.observableArrayList();

	public static void load()
	{
		exampleList.addListener(_instance);
	}

	private ObjectLists()
	{}

	@Override
	public void onChanged(Change<? extends GlobalObject> c)
	{
		c.getList().sort(GlobalRegistry._idComparator);
	}

}
