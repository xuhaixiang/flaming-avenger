package nb.flaming;

public class RouteCmd implements Cmd {

	private Cmd c2a;
	private Condition cond;
	private Cmd c2b;
	private Cmd delegate;

	public RouteCmd(Condition cond, Cmd c2a, Cmd c2b) {
		this.setCond(cond);
		this.setC2a(c2a);
		this.setC2b(c2b);
		this.delegate = c2a;
	}

	@Override
	public void execute() {
		if(!getCond().isSuccess()){
			delegate = getC2b();
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

	public Condition getCond() {
		return cond;
	}

	public void setCond(Condition cond) {
		this.cond = cond;
	}

	public Cmd getC2a() {
		return c2a;
	}

	public void setC2a(Cmd c2a) {
		this.c2a = c2a;
	}

	public Cmd getC2b() {
		return c2b;
	}

	public void setC2b(Cmd c2b) {
		this.c2b = c2b;
	}

}
