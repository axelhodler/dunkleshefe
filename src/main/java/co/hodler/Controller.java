package co.hodler;

import co.hodler.http.ParsedRequest;

public interface Controller {
  String handle(ParsedRequest pr);
}
