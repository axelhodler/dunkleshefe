package co.hodler;

import co.hodler.gateways.StubGreetingsGateway;
import co.hodler.http.ParsedRequest;
import co.hodler.http.Router;
import co.hodler.usecases.PresentGreetingController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class DunklesHefe {

  public static void main(String[] args) throws Exception {
    SocketServer s = new SocketServer();
    s.start(8042, (clientSocket) -> {
      try (
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
      ) {
        Router r = new Router();
        r.register("/", new PresentGreetingController(new View(), new StubGreetingsGateway()));
        String response = r.route(new ParsedRequest(reader.readLine()));
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Length: " + response.length() + "\n");
        out.println(response);
      } catch (IOException e) {
      }
    });
    TimeUnit.MINUTES.sleep(5);
    s.stop();
  }
}
