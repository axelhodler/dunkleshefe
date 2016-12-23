package co.hodler.http;

public class ParsedRequest {
  public String path = "";

  public ParsedRequest(String request) {
    if (request != null) {
      String[] splitRequest = request.split(" ");
      if (splitRequest.length > 1) {
        this.path = splitRequest[1];
      }
    }
  }

}
