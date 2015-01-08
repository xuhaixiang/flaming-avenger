package nb.flaming;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class ChainRouteTest {

	@Test
	public void route_succ() {
		Context t = mock(Context.class);
		Chain<Context> x = route_base(t);
		RouteCmd<Context> rc = (RouteCmd<Context>) x.get(2);
		when(rc.getCond().isSuccess(t)).thenReturn(true);
		x.run(t);
		verify(rc.getC2a()).execute(t);
		verifyNoMoreInteractions(rc.getC2b());
	}

	@Test
	public void route_fail() {
		Context t = mock(Context.class);
		Chain<Context> x = route_base(t);
		RouteCmd<Context> rc = (RouteCmd<Context>) x.get(2);
		when(rc.getCond().isSuccess(t)).thenReturn(true);
		RuntimeException re = new RuntimeException();
		doThrow(re).when(rc.getC2a()).execute(t);
		x.run(t);
		verify(rc.getC2a()).execute(t);
		verifyNoMoreInteractions(rc.getC2b());
		verify(rc.getC2a()).onException(t, re);
		verify(rc.getC2a()).rollback(t);
		verify(x.get(1)).rollback(t);
	}

	@SuppressWarnings("unchecked")
	private Chain<Context> route_base(Context t) {
		Cmd<Context> c = mock(EmptyContextCmd.class);
		Cmd<Context> c1 = mock(EmptyContextCmd.class);
		Cmd<Context> c2A = mock(EmptyContextCmd.class);
		Cmd<Context> c2B = mock(EmptyContextCmd.class);
		Cmd<Context> c3 = mock(EmptyContextCmd.class);
		Condition<Context> cond = mock(Condition.class);
		RouteCmd<Context> rc = new RouteCmd<Context>(cond, c2A, c2B);
		Chain<Context> x = new Chain<Context>();
		x.add(c, c1, rc, c3);
		return x;
	}
}
