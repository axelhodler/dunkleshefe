package co.hodler;

public class View {

  public String toHTML(String greeting) {
    String template = "<html><body><p>${greeting}</p></body></html>";
    return template.replace("${greeting}", greeting);
  }
}
