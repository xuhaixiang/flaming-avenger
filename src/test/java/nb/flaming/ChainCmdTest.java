package nb.flaming;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;

public class ChainCmdTest {

	@Test
	public void chain_add_chain_and_excute_succ(){
		Context t = mock(Context.class);
		Cmd<Context> cmd1 = mock(EmptyContextCmd.class);
		Cmd<Context> cmd2 = mock(EmptyContextCmd.class);
		Chain<Context> x = new Chain<Context>();
		Cmd<Context> cmd3 = mock(EmptyContextCmd.class);
		Cmd<Context> cmd4 = mock(EmptyContextCmd.class);
		ChainCmd<Context> x1 = new ChainCmd<Context>();
		x1.add(cmd3,cmd4);
		Result execute = x.add(cmd1,cmd2,x1).run(t);
		assertTrue(execute.getExecStatus());
		verify(cmd3).execute(t);
		verify(cmd4).execute(t);
	}
	
	@Test
	public void chain_add_chain_and_excute_fail(){
		Context t = mock(Context.class);
		Cmd<Context> cmd1 = mock(EmptyContextCmd.class);
		Cmd<Context> cmd2 = mock(EmptyContextCmd.class);
		Chain<Context> x = new Chain<Context>();
		Cmd<Context> cmd3 = mock(EmptyContextCmd.class);
		Cmd<Context> cmd4 = mock(EmptyContextCmd.class);
		RuntimeException re = new RuntimeException();
		Mockito.doThrow(re).when(cmd4).execute(t);
		ChainCmd<Context> x1 = new ChainCmd<Context>();
		x1.add(cmd3,cmd4);
		Result execute = x.add(cmd1,cmd2,x1).run(t);
		assertFalse(execute.getExecStatus());
		verify(cmd3).execute(t);
		verify(cmd4).execute(t);
		verify(cmd4).rollback(t);
		verify(cmd2).rollback(t);
	}

}
