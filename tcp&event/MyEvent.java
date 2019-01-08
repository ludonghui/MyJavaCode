package tcp;

import java.net.Socket;
import java.util.EventObject;

public class MyEvent extends EventObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Socket s;

	public MyEvent(Object source, Socket s) {
		super(source);
		this.s = s;
	}

	public Socket getSocket() {
		return this.s;
	}

}
