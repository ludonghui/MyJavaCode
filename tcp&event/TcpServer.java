package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.EventListener;
import java.util.EventObject;

public class TcpServer extends Thread {
	private int port;
	boolean isListenFlag = false;
	ServerSocket server;
	private MyListener myListener;

	TcpServer(int port) {
		this.port = port;	
	}

	public void Start() {
		try {
			Thread t = new Thread(this);
			t.start();
		} catch (Exception e) {

		}
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(port);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		isListenFlag = true;
		System.out.println("开始监听");
		while (isListenFlag) {
			try {
				Socket client = server.accept();

				System.out.println("接收到客户端连接");
				
				if(myListener!=null) {
					MyEvent myEvent=new MyEvent(this,client);
					notifyListeners(myEvent);
				}

//				RecvDateThread RecvT = new RecvDateThread(client);
//				RecvT.start();
//				System.out.println("开启接收数据线程");

			} catch (Exception e) {
			}
		}
	}

	public void close() {
		try {
			isListenFlag = false;
		} catch (Exception e) {

		}
	}

	private void notifyListeners(MyEvent event) {
		myListener.myEvent(event);
	}

}





class ListenClient {
	Socket s;
	DataInputStream in;
	DataOutputStream out;

	ListenClient(Socket s) {
		this.s = s;
		try {
			in = new DataInputStream(this.s.getInputStream());
			out = new DataOutputStream(this.s.getOutputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void send(byte[] bytes) {
		if (out != null) {
			try {
				out.write(bytes);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}