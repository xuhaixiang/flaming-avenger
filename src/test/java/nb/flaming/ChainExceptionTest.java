package nb.flaming;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class ChainExceptionTest {

	@Test
	public void onExceptionNotify() {
		Cmd c = mock(Cmd.class);
		Cmd c1 = mock(Cmd.class);
		Chain x = new Chain();
		doThrow(RuntimeException.class).when(c1).execute();
		x.add(c, c1).execute();
		verify(c1).onException();
	}
}
