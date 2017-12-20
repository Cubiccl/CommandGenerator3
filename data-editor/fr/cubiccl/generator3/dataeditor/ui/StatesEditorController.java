package fr.cubiccl.generator3.dataeditor.ui;

import java.net.URL;
import java.util.*;

import javafx.collections.ListChangeListener;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import fr.cubiccl.generator3.dataeditor.TestApplication;
import fr.cubiccl.generator3.dataeditor.TestPersistance;
import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.BlockState;

public class StatesEditorController implements Initializable
{
	public ComboBox<String> comboboxType;
	public ListView<String> listStates, listValues;
	private HashMap<String, List<String>> states;
	public TextField textfieldState, textfieldValue;

	public void addState()
	{
		String state = this.textfieldState.getText();
		if (!state.equals("") && !this.listStates.getItems().contains(state))
		{
			this.states.put(state, new ArrayList<String>());
			this.listStates.getItems().add(state);
			this.listStates.getItems().sort(Comparator.naturalOrder());
			this.listStates.getSelectionModel().select(state);
		}
	}

	public void addValue()
	{
		String value = this.textfieldValue.getText();
		if (!value.equals("") && !this.listValues.getItems().contains(value))
		{
			this.states.get(this.selectedState()).add(value);
			this.listValues.getItems().add(value);
			this.listValues.getItems().sort(Comparator.naturalOrder());
			this.listValues.getSelectionModel().select(value);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		this.states = new HashMap<String, List<String>>();
		this.comboboxType.getItems().setAll("String", "Boolean", "Integer");
		this.listStates.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<String>()
		{
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> c)
			{
				onStateSelected(c.getList().get(0));
			}
		});

		this.onReset();
	}

	private void onCancel()
	{
		TestApplication.instance.setScene(TestApplication.VERSION_OBJECTS);
	}

	public void onFinished()
	{
		Block block = TestPersistance.editedBlock;
		block.blockStates.clear();
		for (String stateID : this.listStates.getItems())
		{
			BlockState state = new BlockState(stateID, (byte) this.comboboxType.getSelectionModel().getSelectedIndex(), this.states.get(stateID).toArray(
					new String[0]));
			block.addBlockState(state);
		}
		this.onCancel();
	}

	public void onReset()
	{
		this.states.clear();
		this.listStates.getItems().clear();
		for (BlockState state : TestPersistance.editedBlock.blockStates.values())
		{
			this.states.put(state.id, Arrays.asList(state.values));
			this.listStates.getItems().add(state.id);
		}
		this.listStates.getItems().sort(Comparator.naturalOrder());
	}

	protected void onStateSelected(String state)
	{
		if (state == null) this.listValues.getItems().clear();
		else this.listValues.getItems().setAll(this.states.get(state));
	}

	public void onValueDown()
	{
		int index = this.listValues.getSelectionModel().getSelectedIndex();
		if (index == this.listValues.getItems().size() - 1) return;
		String state = this.selectedState();
		String tmp = this.states.get(state).get(index + 1);
		this.states.get(state).set(index + 1, this.listValues.getItems().get(index));
		this.states.get(state).set(index, tmp);

		this.onStateSelected(state);
		this.listValues.getSelectionModel().select(index + 1);
	}

	public void onValueUp()
	{
		int index = this.listValues.getSelectionModel().getSelectedIndex();
		if (index == 0) return;
		String state = this.selectedState();
		String tmp = this.states.get(state).get(index - 1);
		this.states.get(state).set(index - 1, this.listValues.getItems().get(index));
		this.states.get(state).set(index, tmp);

		this.onStateSelected(state);
		this.listValues.getSelectionModel().select(index - 1);
	}

	public void removeState()
	{
		String state = this.selectedState();
		if (state != null)
		{
			this.listStates.getItems().remove(state);
			this.states.remove(state);
		}
	}

	public void removeValue()
	{
		String value = this.listValues.getSelectionModel().getSelectedItem();
		if (value != null)
		{
			this.listValues.getItems().remove(value);
			this.states.get(this.selectedState()).remove(value);
		}
	}

	private String selectedState()
	{
		return this.listStates.getSelectionModel().getSelectedItem();
	}

}
