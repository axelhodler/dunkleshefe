package co.hodler.usecases;

import co.hodler.Controller;
import co.hodler.View;
import co.hodler.gateways.StubGreetingsGateway;
import co.hodler.http.ParsedRequest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class PresentGreetingControllerShould {
  @Test
  void prepare_view() {
    Controller viewContent = new PresentGreetingController(
      new View(), new StubGreetingsGateway());

    assertThat(viewContent.handle(
      new ParsedRequest(null)), containsString("Hello World"));
  }
}
