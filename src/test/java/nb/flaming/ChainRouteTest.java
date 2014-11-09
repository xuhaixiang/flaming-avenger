package nb.flaming;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class ChainRouteTest {

	@Test
	public void route_succ(){
		Chain x = route_base();
		RouteCmd rc = (RouteCmd)x.get(2);
		when(rc.getCond().isSuccess()).thenReturn(true);
		x.execute();
		verify(rc.getC2a()).execute();
		verifyNoMoreInteractions(rc.getC2b());
	}
	
	@Test
	public void route_fail(){
		Chain x = route_base();
		RouteCmd rc = (RouteCmd)x.get(2);
		when(rc.getCond().isSuccess()).thenReturn(true);
		doThrow(RuntimeException.class).when(rc.getC2a()).execute();
		x.execute();
		verify(rc.getC2a()).execute();
		verifyNoMoreInteractions(rc.getC2b());
		verify(rc.getC2a()).onException();
		verify(rc.getC2a()).rollback();
		verify(x.get(1)).rollback();
	}
	
	private Chain route_base(){
		Cmd c = mock(Cmd.class);
		Cmd c1 = mock(Cmd.class);
		Cmd c2A = mock(Cmd.class);
		Cmd c2B = mock(Cmd.class);
		Cmd c3 = mock(Cmd.class);
		Condition cond = mock(Condition.class);
		RouteCmd rc = new RouteCmd(cond, c2A, c2B);
		Chain x = new Chain();
		x.add(c,c1, rc, c3);
		return x;
	}
}
