package nb.flaming;


public class Chain {

	public Chain startWith(Cmd printCmd) {
		return this;
	}

	public Boolean execute() {
		return Boolean.TRUE;
	}

}
