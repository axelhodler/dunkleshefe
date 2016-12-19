package co.hodler;

import java.io.IOException;

public class DunklesHefe {

  public static void main(String[] args) throws IOException {
    SocketServer s = new SocketServer();
    s.start(8042);
  }
}
