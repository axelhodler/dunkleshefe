package co.hodler;

public class View {
  private final String greeting;

  public View(String greeting) {
    this.greeting = greeting;
  }

  public String toHTML() {
    String template = "<html><body><p>${greeting}</p></body></html>";
    return template.replace("${greeting}", this.greeting);
  }
}
