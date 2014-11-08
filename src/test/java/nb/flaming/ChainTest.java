package nb.flaming;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;

public class ChainTest {

	@Test
	public void run_empty_return_true() {
		Chain x = new Chain();
		Result result = x.execute();
		assertTrue(result.getExec());
	}

	@Test
	public void add_one_cmd_and_execute_succ() {
		Cmd cmd = mock(Cmd.class);
		Chain x = new Chain();
		x.add(cmd);
		assertTrue(x.execute().getExec());
		verify(cmd).execute();
	}

	@Test
	public void add_more_cmd_and_execute_succ() {
		Cmd cmd1 = mock(Cmd.class);
		Cmd cmd2 = mock(Cmd.class);
		Cmd cmd3 = mock(Cmd.class);
		Chain x = new Chain();
		Result execute = x.add(cmd1).add(cmd2).add(cmd3).execute();
		assertTrue(execute.getExec());
		verify(cmd1).execute();
		verify(cmd2).execute();
		verify(cmd3).execute();
	}

	@Test
	public void add_more_cmd_and_execute_fail() {
		Cmd cmd1 = mock(Cmd.class);
		Cmd cmd2 = mock(Cmd.class);
		Cmd cmd3 = mock(Cmd.class);
		doThrow(RuntimeException.class).when(cmd2).execute();
		Chain x = new Chain();
		Result execute = x.add(cmd1).add(cmd2).add(cmd3).execute();
		assertFalse(execute.getExec());
		verify(cmd1).execute();
		verify(cmd2).execute();
		verifyNoMoreInteractions(cmd3);
	}

	@Test
	public void rollback_when_execute_fail() {
		Cmd cmd1 = mock(Cmd.class);
		Cmd cmd2 = mock(Cmd.class);
		Cmd cmd3 = mock(Cmd.class);
		doThrow(RuntimeException.class).when(cmd2).execute();
		Chain x = new Chain();
		Result execute = x.add(cmd1, cmd2, cmd3).execute();
		assertFalse(execute.getExec());
		assertTrue(execute.getRollBack());
		verify(cmd1).execute();
		verify(cmd2).execute();
		verify(cmd2).rollback();
		verify(cmd1).rollback();

	}
}
