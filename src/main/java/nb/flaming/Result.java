package nb.flaming;

public class Result {

	private Boolean execStatus;
	private Boolean rollBackStatus;

	public Result(boolean b, boolean c) {
		execStatus = b;
		rollBackStatus = c;
	}

	public Boolean getExecStatus() {
		return execStatus;
	}

	public Boolean getRollBackStatus() {
		return rollBackStatus;
	}

	public void setExecStatus(Boolean exec) {
		this.execStatus = exec;
	}

	public void setRollBackStatus(Boolean rollBack) {
		this.rollBackStatus = rollBack;
	}

	@Override
	public String toString() {
		return "Result [exec=" + execStatus + ", rollBack=" + rollBackStatus + "]";
	}

	public static Result EXEC_SUCC() {
		return new Result(true, false);
	}

	public static Result ROLL_SUCC() {
		return new Result(false, true);
	}

	public static Result ROLL_FAIL() {
		return new Result(false, false);
	}

}
