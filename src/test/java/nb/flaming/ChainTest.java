package nb.flaming;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;

@SuppressWarnings("unchecked")
public class ChainTest {

	@Test
	public void run_empty_return_true() {
		Context t = mock(Context.class);
		Chain<Context> x = new Chain<Context>();
		Result result = x.run(t);
		assertTrue(result.getExecStatus());
	}

	@Test
	public void add_one_cmd_and_execute_succ() {
		Context t = mock(Context.class);
		Cmd<Context> cmd = mock(EmptyContextCmd.class);
		Chain<Context> x = new Chain<Context>();
		x.add(cmd);
		assertTrue(x.run(t).getExecStatus());
		verify(cmd).execute(t);
	}

	@Test
	public void add_more_cmd_and_execute_succ() {
		Context t = mock(Context.class);
		Cmd<Context> cmd1 = mock(EmptyContextCmd.class);
		Cmd<Context> cmd2 = mock(EmptyContextCmd.class);
		Cmd<Context> cmd3 = mock(EmptyContextCmd.class);
		Chain<Context> x = new Chain<Context>();
		Result execute = x.add(cmd1).add(cmd2).add(cmd3).run(t);
		assertTrue(execute.getExecStatus());
		verify(cmd1).execute(t);
		verify(cmd2).execute(t);
		verify(cmd3).execute(t);
	}

	@Test
	public void add_more_cmd_and_execute_fail() {
		Context t = mock(Context.class);
		Cmd<Context> cmd1 = mock(EmptyContextCmd.class);
		Cmd<Context> cmd2 = mock(EmptyContextCmd.class);
		Cmd<Context> cmd3 = mock(EmptyContextCmd.class);
		doThrow(RuntimeException.class).when(cmd2).execute(t);
		Chain<Context> x = new Chain<Context>();
		Result execute = x.add(cmd1).add(cmd2).add(cmd3).run(t);
		assertFalse(execute.getExecStatus());
		verify(cmd1).execute(t);
		verify(cmd2).execute(t);
		verifyNoMoreInteractions(cmd3);
	}

	@Test
	public void rollback_when_execute_fail() {
		Context t = mock(Context.class);
		Cmd<Context> cmd1 = mock(EmptyContextCmd.class);
		Cmd<Context> cmd2 = mock(EmptyContextCmd.class);
		Cmd<Context> cmd3 = mock(EmptyContextCmd.class);
		doThrow(RuntimeException.class).when(cmd2).execute(t);
		Chain<Context> x = new Chain<Context>();
		Result execute = x.add(cmd1, cmd2, cmd3).run(t);
		assertFalse(execute.getExecStatus());
		assertTrue(execute.getRollBackStatus());
		verify(cmd1).execute(t);
		verify(cmd2).execute(t);
		verify(cmd2).rollback(t);
		verify(cmd1).rollback(t);

	}
}