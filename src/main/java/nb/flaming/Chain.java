package nb.flaming;

import java.util.Deque;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;

public class Chain {

	private List<Cmd> z = Lists.newArrayList();

	public Result execute() {
		Deque<Cmd> execs = Queues.newArrayDeque(z);
		Deque<Cmd> rollBacks = Queues.newArrayDeque();
		return run(execs, rollBacks);
	}

	private Result rollBack(Deque<Cmd> build) {
		if (build.isEmpty()) {
			return Result.ROLL_SUCC();
		}
		try {
			build.pop().rollback();
			return rollBack(build);
		} catch (Exception e) {
			return Result.ROLL_FAIL();
		}
	}

	private Result run(Deque<Cmd> build, Deque<Cmd> rollBackCmds) {
		if (build.isEmpty()) {
			return Result.EXEC_SUCC();
		}
		Cmd c = build.pop();
		rollBackCmds.push(c);
		try {
			c.execute();
			return run(build, rollBackCmds);
		} catch (Exception e) {
			c.onException();
			return rollBack(rollBackCmds);
		}
	}

	public Chain add(Cmd... cmds) {
		for (Cmd c : cmds) {
			z.add(c);
		}
		return this;
	}

}
