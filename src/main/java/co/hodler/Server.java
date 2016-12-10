package co.hodler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8042);

    while (true) {
      try (
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
      ) {
        out.println("HTTP/1.1 200 OK\r\n");
        out.println("<html><body><p>Hello World</p></body></html>");
        out.flush();
      }
    }
  }
}
