package nb.flaming;

public interface Cmd<T extends Context> {

	public abstract void execute(T t);

	public abstract void rollback(T t);

	public abstract void onException(T t, Exception e);

}
