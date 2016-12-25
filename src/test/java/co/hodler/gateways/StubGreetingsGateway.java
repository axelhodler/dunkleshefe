package co.hodler.gateways;

public class StubGreetingsGateway implements GreetingsGateway {
  @Override
  public String fetchGreetingByLocale(String locale) {
    return "Hello World";
  }
}
