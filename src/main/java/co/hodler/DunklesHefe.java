package co.hodler;

import java.util.concurrent.TimeUnit;

public class DunklesHefe {

  public static void main(String[] args) throws Exception {
    SocketServer s = new SocketServer(new View().toHTML());
    s.start(8042);
    TimeUnit.MINUTES.sleep(5);
    s.stop();
  }
}
