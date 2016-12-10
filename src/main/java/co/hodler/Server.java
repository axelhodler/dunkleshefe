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
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
      ) {
        String s;
        while ((s = in.readLine()) != null) {
          System.out.println(s);
          if (s.isEmpty()) {
            break;
          }
        }
        out.write("HTTP/1.0 200 OK\r\n");
      }
    }
  }
}
