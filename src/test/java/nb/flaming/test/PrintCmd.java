package nb.flaming.test;

import nb.flaming.Cmd;

public class PrintCmd implements Cmd {
	
	@Override
	public void execute(){
		System.out.println("print cmd execute.");
	}

}
