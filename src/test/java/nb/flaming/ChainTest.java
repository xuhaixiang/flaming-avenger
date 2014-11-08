package nb.flaming;

import static org.junit.Assert.assertTrue;
import nb.flaming.test.PrintCmd;

import org.junit.Test;

public class ChainTest {

	@Test
	public void addCmd_And_execute_Succ(){
		Chain x = new Chain();
		x.startWith(new PrintCmd());
		assertTrue(x.execute());
	}
}
