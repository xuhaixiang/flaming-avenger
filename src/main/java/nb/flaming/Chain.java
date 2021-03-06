package nb.flaming;

import java.util.Deque;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;

public class Chain<T extends Context> {

	private List<Cmd<T>> z = Lists.newArrayList();
	
	private Exception e;

	public Result run(T t) {
		Deque<Cmd<T>> execs = Queues.newArrayDeque(z);
		Deque<Cmd<T>> rollBacks = Queues.newArrayDeque();
		return run(t, execs, rollBacks);
	}

	protected Result rollBack(T t, Deque<Cmd<T>> build) {
		if (build.isEmpty()) {
			return Result.ROLL_SUCC();
		}
		try {
			build.pop().rollback(t);
			return rollBack(t, build);
		} catch (Exception e) {
			return Result.ROLL_FAIL();
		}
	}

	protected Result run(T t, Deque<Cmd<T>> build, Deque<Cmd<T>> rollBackCmds) {
		if (build.isEmpty()) {
			return Result.EXEC_SUCC();
		}
		Cmd<T> c = build.pop();
		rollBackCmds.push(c);
		try {
			c.execute(t);
			return run(t, build, rollBackCmds);
		} catch (Exception e) {
			this.e = e;
			c.onException(t, e);
			return rollBack(t, rollBackCmds);
		}
	}

	public Chain<T> add(Cmd<T>... cmds) {
		for (Cmd<T> c : cmds) {
			z.add(c);
		}
		return this;
	}
	
	protected Cmd<T> get(int idx){
		return z.get(idx);
	}
	
	protected List<Cmd<T>> get(){
		return z;
	}
	
	protected Exception getException(){
		return e;
	}

}
