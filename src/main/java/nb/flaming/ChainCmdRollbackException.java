package nb.flaming;

public class ChainCmdRollbackException extends RuntimeException {

	private static final long serialVersionUID = 7875367219362773230L;

	public ChainCmdRollbackException() {
		super();
	}

	public ChainCmdRollbackException(Throwable cause) {
		super(cause);
	}

}
