package nb.flaming;

public interface Cmd {

	public abstract void execute();

	public abstract void rollback();

	public abstract void onException();

}
