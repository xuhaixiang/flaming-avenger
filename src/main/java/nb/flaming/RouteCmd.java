package nb.flaming;

public class RouteCmd<T extends Context> implements Cmd<T> {

	private Cmd<T> c2a;
	private Condition<T> cond;
	private Cmd<T> c2b;
	private Cmd<T> delegate;

	public RouteCmd(Condition<T> cond, Cmd<T> c2a, Cmd<T> c2b) {
		this.setCond(cond);
		this.setC2a(c2a);
		this.setC2b(c2b);
		this.delegate = c2a;
	}

	@Override
	public void execute(T t) {
		if(!getCond().isSuccess(t)){
			delegate = getC2b();
		}
		delegate.execute(t);
	}

	@Override
	public void rollback(T t) {
		delegate.rollback(t);
	}

	@Override
	public void onException(T t) {
		delegate.onException(t);
	}

	public Condition<T> getCond() {
		return cond;
	}

	public void setCond(Condition<T> cond) {
		this.cond = cond;
	}

	public Cmd<T> getC2a() {
		return c2a;
	}

	public void setC2a(Cmd<T> c2a) {
		this.c2a = c2a;
	}

	public Cmd<T> getC2b() {
		return c2b;
	}

	public void setC2b(Cmd<T> c2b) {
		this.c2b = c2b;
	}

}
