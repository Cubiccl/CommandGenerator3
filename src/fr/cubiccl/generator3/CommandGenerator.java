package fr.cubiccl.generator3;



public class CommandGenerator
{
	
	public static MainApplication window;
	
	public static void main(String[] args)
	{
		MainApplication.initialize(args);
	}

	public static void exit()
	{
		window.primaryStage.close();
	}
}
