package bitcamp.report;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientApp {

  public static void main(String[] args) throws Exception {
    Socket socket = new Socket("localhost", 8888);

    OutputStream out = socket.getOutputStream();
    InputStream in = socket.getInputStream();

    out.write(100);
  }
}
