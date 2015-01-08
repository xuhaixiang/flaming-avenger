package nb.flaming;

public class ChainCmd<T extends Context> extends Chain<T> implements Cmd<T> {

	@Override
	public void execute(T t) {
		Result result = run(t);
		if (!result.getExecStatus()) {
			if (result.getRollBackStatus()) {
				throw new ChainCmdException();
			} else {
				throw new ChainCmdRollbackException();
			}
		}
	}

	@Override
	public void rollback(T t) {
	}

	@Override
	public void onException(T t, Exception e) {
	}

}
