package nb.flaming;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class ChainTest {

	@Test
	public void add_one_cmd_and_execute_succ() {
		Cmd cmd = mock(Cmd.class);
		Chain x = new Chain();
		x.add(cmd);
		assertTrue(x.execute());
		verify(cmd).execute();
	}

	@Test
	public void add_more_cmd_and_execute_succ() {
		Cmd cmd1 = mock(Cmd.class);
		Cmd cmd2 = mock(Cmd.class);
		Chain x = new Chain();
		Boolean execute = x.add(cmd1).add(cmd2).execute();
		assertTrue(execute);
		verify(cmd1).execute();
		verify(cmd2).execute();

	}
}
