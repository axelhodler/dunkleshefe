package co.hodler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
  private boolean running = true;
  private ServerSocket serverSocket;
  private ExecutorService executorService = Executors.newSingleThreadExecutor();

  public void start(int port) throws IOException {
    serverSocket = new ServerSocket(port);

    executorService.execute(() -> {
      while (running) {
        try (
          Socket clientSocket = serverSocket.accept();
          PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
        ) {
          out.println("HTTP/1.1 200 OK\r\n");
          out.println("<html><body><p>Hello World</p></body></html>");
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
