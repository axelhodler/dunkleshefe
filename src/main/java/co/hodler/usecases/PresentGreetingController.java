package co.hodler.usecases;

import co.hodler.Controller;
import co.hodler.View;
import co.hodler.http.ParsedRequest;

public class PresentGreetingController implements Controller {
  private View view;

  public PresentGreetingController(View view) {
    this.view = view;
  }

  @Override
  public String handle(ParsedRequest pr) {
    return view.toHTML("Hello World");
  }
}
