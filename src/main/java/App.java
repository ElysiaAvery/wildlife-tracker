import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("sightings", Sightings.all());
      model.put("animals", Animals.all());
      model.put("endangeredAnimals", EndangeredAnimals.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sightings", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String location = request.queryParams("location");
      String rangerName = request.queryParams("ranger_name");
      Sightings newSighting = new Sightings(location, rangerName);
      newSighting.save();
      model.put("spotted", newSighting.getSpotted());
      model.put("header", "templates/header.vtl");
      model.put("template", "templates/sighting-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/sighting/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Sightings sightingId = Sightings.find(Integer.parseInt(request.params(":id")));
      model.put("sighting", sightingId);
      model.put("header", "templates/header.vtl");
      model.put("template", "templates/sighting.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
