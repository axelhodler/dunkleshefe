package co.hodler.http;

import co.hodler.Controller;

import java.util.HashMap;
import java.util.Map;

public class Router {
  Map<String, Controller> registeredRoutes = new HashMap<>();

  public void register(String path, Controller controller) {
    registeredRoutes.put(path, controller);
  }

  public String route(ParsedRequest pr) {
    if (registeredRoutes.containsKey(pr.getPath()))
      return registeredRoutes.get(pr.getPath()).handle(pr);
    else
      return "Not found";
  }
}
