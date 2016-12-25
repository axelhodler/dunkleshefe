package co.hodler.http;


import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ParsedRequestShould {
  @Test
  void deal_with_empty_strings() {
    ParsedRequest parsedRequest = new ParsedRequest("");

    assertThat(parsedRequest.getPath(), is(""));
  }

  @Test
  void deal_with_null() {
    ParsedRequest parsedRequest = new ParsedRequest(null);

    assertThat(parsedRequest.getPath(), is(""));
  }

  @Test
  void deals_with_nonsense() {
    ParsedRequest parsedRequest = new ParsedRequest("yooooo");

    assertThat(parsedRequest.getPath(), is(""));
  }

  @Test
  void extract_the_path() {
    ParsedRequest parsedRequest = new ParsedRequest("GET / HTTP/1.1");

    assertThat(parsedRequest.getPath(), is("/"));
  }


}
