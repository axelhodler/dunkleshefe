package co.hodler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
  public void start(int port) throws IOException {
    ServerSocket serverSocket = new ServerSocket(port);

    while (true) {
      try (
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
      ) {
        out.println("HTTP/1.1 200 OK\r\n");
        out.println("<html><body><p>Hello World</p></body></html>");
      }
    }
  }
}
