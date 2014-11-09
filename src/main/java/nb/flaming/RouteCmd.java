package nb.flaming;

public class RouteCmd implements Cmd {

	private Cmd c2a;
	private Condition cond;
	private Cmd c2b;
	private Cmd delegate;

	public RouteCmd(Condition cond, Cmd c2a, Cmd c2b) {
		this.cond = cond;
		this.c2a = c2a;
		this.c2b = c2b;
		this.delegate = c2a;
	}

	@Override
	public void execute() {
		if(!cond.isSuccess()){
			delegate = c2b;
		}
		delegate.execute();
	}

	@Override
	public void rollback() {
		delegate.rollback();
	}

	@Override
	public void onException() {
		delegate.onException();
	}

}
