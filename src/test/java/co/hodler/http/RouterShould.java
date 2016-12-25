package co.hodler.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RouterShould {
  Router r;

  @BeforeEach
  void setUp() {
    r = new Router();
  }

  @Test
  void route_paths_to_registered_controller() {
    String handledRequestResponse = "handledRequestResponse";
    r.register("/foo", (s) -> {
      return handledRequestResponse;
    });
    ParsedRequest pr = new ParsedRequest("IRRELEVANT /foo");

    assertThat(r.route(pr), is(handledRequestResponse));
  }

  @Test
  void handles_multiple_routes() {
    String handledRequestResponse = "response";
    r.register("/bar", (s) -> {
      return handledRequestResponse;
    });
    ParsedRequest pr = new ParsedRequest("IRRELEVANT /bar");

    assertThat(r.route(pr), is(handledRequestResponse));
  }

  @Test
  void uses_not_found_if_route_not_present() {
    ParsedRequest pr = new ParsedRequest("IRRELEVANT /favicon.ico");

    assertThat(r.route(pr), is("Not found"));
  }
}
