package co.hodler;

import org.junit.jupiter.api.Test;

public class SocketServerShould {

  @Test
  void free_the_port_when_stopping() throws Exception {
    SocketServer socketServer = new SocketServer();
    socketServer.start(4444);
    socketServer.stop();
    socketServer.start(4444);
  }
}
