package co.hodler;

import co.hodler.http.ParsedRequest;
import co.hodler.http.Router;
import co.hodler.usecases.PresentGreetingController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
  private boolean running = true;
  private ServerSocket serverSocket;
  private ExecutorService executorService;

  public void start(int port) throws Exception {
    executorService = Executors.newSingleThreadExecutor();
    serverSocket = new ServerSocket(port);

    executorService.execute(() -> {
      while (running) {
        try (
          Socket clientSocket = serverSocket.accept();
          BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
        ) {
          Router r = new Router();
          r.register("/", new PresentGreetingController(new View("Hello World")));
          String response = r.route(new ParsedRequest(reader.readLine()));
          out.println("HTTP/1.1 200 OK");
          out.println("Content-Length: " + response.length() + "\n");
          out.println(response);
        } catch (IOException e) {
        }
      }
    });
  }

  public void stop() throws Exception {
    this.running = false;
    serverSocket.close();
    executorService.shutdownNow();
  }
}
