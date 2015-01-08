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
		Cmd<Context> c = mock(EmptyContextCmd.class);
		Cmd<Context> c1 = mock(EmptyContextCmd.class);
		Chain<Context> x = new Chain<Context>();
		RuntimeException re = new RuntimeException();
		doThrow(re).when(c1).execute(t);
		x.add(c, c1).run(t);
		verify(c1).onException(t, re);
	}
}
