package nb.flaming;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

@SuppressWarnings("unchecked")
public class ChainExceptionTest {

	@Test
	public void onExceptionNotify() {
		Context t = mock(Context.class);
		Cmd<Context> c = mock(CmdContext.class);
		Cmd<Context> c1 = mock(CmdContext.class);
		Chain<Context> x = new Chain<Context>();
		doThrow(RuntimeException.class).when(c1).execute(t);
		x.add(c, c1).execute(t);
		verify(c1).onException(t);
	}
}
