package nb.flaming;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class ChainRouteTest {

	@Test
	public void route_succ(){
		Cmd c = mock(Cmd.class);
		Cmd c1 = mock(Cmd.class);
		Cmd c2A = mock(Cmd.class);
		Cmd c2B = mock(Cmd.class);
		Cmd c3 = mock(Cmd.class);
		Condition cond = mock(Condition.class);
		RouteCmd rc = new RouteCmd(cond, c2A, c2B);
		when(cond.isSuccess()).thenReturn(true);
		Chain x = new Chain();
		x.add(c,c1, rc, c3).execute();
		verify(c2A).execute();
		verifyNoMoreInteractions(c2B);
	}
}
