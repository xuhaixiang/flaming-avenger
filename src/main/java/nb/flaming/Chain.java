package nb.flaming;

import java.util.List;

import com.google.common.collect.Lists;

public class Chain {
	
	private List<Cmd> z = Lists.newArrayList();

	public Boolean execute() {
		for(Cmd c : z){
			c.execute();
		}
		return Boolean.TRUE;
	}

	public Chain add(Cmd cmd) {
		z.add(cmd);
		return this;
	}

}
