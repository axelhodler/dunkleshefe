package co.hodler.usecases;

import co.hodler.Controller;
import co.hodler.View;
import co.hodler.gateways.GreetingsGateway;
import co.hodler.http.ParsedRequest;

public class PresentGreetingController implements Controller {
  private final View view;
  private final GreetingsGateway greetings;

  public PresentGreetingController(View view, GreetingsGateway greetings) {
    this.view = view;
    this.greetings = greetings;
  }

  @Override
  public String handle(ParsedRequest pr) {
    return view.toHTML(this.greetings.fetchGreetingByLocale("en_US"));
  }
}
