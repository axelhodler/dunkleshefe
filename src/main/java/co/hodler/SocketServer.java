package co.hodler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
  private String content;
  private boolean running = true;
  private ServerSocket serverSocket;
  private ExecutorService executorService;

  public SocketServer(String content) {
    this.content = content;
  }

  public void start(int port) throws Exception {
    executorService = Executors.newSingleThreadExecutor();
    serverSocket = new ServerSocket(port);

    executorService.execute(() -> {
      while (running) {
        try (
          Socket clientSocket = serverSocket.accept();
          PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
        ) {
          out.println("HTTP/1.1 200 OK");
          out.println("Content-Length: " + this.content.length() + "\r\n");
          out.println(this.content);
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
