package co.hodler;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class SocketServerShould {

  private SocketServer socketServer;

  @BeforeEach
  void setUp() {
    socketServer = new SocketServer();
  }

  @Test
  void free_the_port_when_stopping() throws Exception {
    socketServer.start(4444);
    socketServer.stop();
    socketServer.start(4444);
  }

  @Test
  void provides_response_body() throws Exception {
    socketServer.start(4444);
    URL localhost = new URL("http://localhost:4444/");
    URLConnection con = localhost.openConnection();
    String response = "";
    try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
      response = in.lines().collect(Collectors.joining("\n"));
    }

    assertThat(response, containsString("Hello World"));
  }

  @AfterEach
  void cleanUp() throws Exception {
    socketServer.stop();
  }
}
