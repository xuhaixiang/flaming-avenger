package nb.flaming;

public class Result {

	private Boolean exec;
	private Boolean rollBack;

	public Result(boolean b, boolean c) {
		exec = b;
		rollBack = c;
	}

	public Boolean getExec() {
		return exec;
	}

	public Boolean getRollBack() {
		return rollBack;
	}

	public void setExec(Boolean exec) {
		this.exec = exec;
	}

	public void setRollBack(Boolean rollBack) {
		this.rollBack = rollBack;
	}

	@Override
	public String toString() {
		return "Result [exec=" + exec + ", rollBack=" + rollBack + "]";
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
