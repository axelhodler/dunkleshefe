package co.hodler;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

@Disabled
public class SocketServerShould {

  private SocketServer socketServer;

  @AfterEach
  void cleanUp() throws Exception {
    socketServer.stop();
  }

  @Test
  void free_the_port_when_stopping() throws Exception {
    socketServer = new SocketServer();
    socketServer.start(4444);
    socketServer.stop();
    socketServer.start(4444);
  }

  @Test
  void provides_response_body() throws Exception {
    String content = "<html><body><p>Hello World</p></body></html>";
    socketServer = new SocketServer();
    socketServer.start(4444);
    URL localhost = new URL("http://localhost:4444/");
    URLConnection con = localhost.openConnection();
    String response = readResponseBody(con);

    assertThat(response, containsString("Hello World"));
  }

  @Test
  void is_not_hardcoding_content_length() throws Exception {
    String content = "<html><body><p>Hallo Welt</p></body></html>";
    socketServer = new SocketServer();
    socketServer.start(4444);
    URL localhost = new URL("http://localhost:4444/");
    URLConnection con = localhost.openConnection();
    String response = readResponseBody(con);

    assertThat(response, containsString("Hallo Welt"));
  }

  private String readResponseBody(URLConnection con) throws IOException {
    String response = "";
    try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
      response = in.lines().collect(Collectors.joining("\n"));
    }
    return response;
  }
}
