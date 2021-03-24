import models.Animal;
import models.Locations;
import models.Rangers;
import models.Sighting;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        /*About ANIMAL*/
        get("/animals",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"animals.hbs");
        },new HandlebarsTemplateEngine());

        get("/new/animals",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"animal-form.hbs");
        },new HandlebarsTemplateEngine());


        post("/new/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int animalId = Integer.parseInt(request.queryParams("id"));
            int animalAge = Integer.parseInt(request.queryParams("age"));
            String animalName = request.queryParams("name");
            Animal animal = new Animal( animalId, animalAge, animalName);
            animal.save();
            model.put("animals", Animal.all());
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.all());
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());
        /*****************************************************************************/

        /*ABOUT RANGERS*/
        get("rangers/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "ranger-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/rangers", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int badge = Integer.parseInt(request.queryParams("badge"));
            Rangers ranger = new Rangers(name,badge);
            ranger.save();
            model.put("rangers", Rangers.all());
            model.put("template", "templates/rangers.vtl");
            return new ModelAndView(model, "rangers.hbs");
        }, new HandlebarsTemplateEngine());

        get("/rangers", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("rangers", Rangers.all());
            return new ModelAndView(model, "rangers.hbs");
        }, new HandlebarsTemplateEngine());

        post("/rangers/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Rangers ranger = Rangers.find(Integer.parseInt(request.params(":id")));
            ranger.delete();
            model.put("rangers", Rangers.all());
            return new ModelAndView(model,"rangers.hbs");
        }, new HandlebarsTemplateEngine());

        /*****************************************************************************/

        /*ABOUT LOCATION*/
        get("locations/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "location-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/locations", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            Locations location = new Locations(name);
            location.save();
            model.put("locations", Locations.all());
            return new ModelAndView(model, "locations.hbs");
        }, new HandlebarsTemplateEngine());

        get("/locations", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("locations", Locations.all());
            return new ModelAndView(model, "locations.hbs");
        }, new HandlebarsTemplateEngine());

        post("/locations/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Locations location = Locations.find(Integer.parseInt(request.params(":id")));
            location.delete();
            model.put("locations", Locations.all());
            return new ModelAndView(model,"locations.hbs");
        }, new HandlebarsTemplateEngine());

        /****************************************************************************************/

        /*ABOUT SIGHTINGS*/
        get("sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animal.all());
            model.put("locations", Locations.all());
            model.put("rangers", Rangers.all());
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int sightingId = Integer.parseInt(request.queryParams("sightingId"));
            int animalId = Integer.parseInt(request.queryParams("animalId"));
            int rangerId = Integer.parseInt(request.queryParams("rangerId"));
            int locationId = Integer.parseInt(request.queryParams("locationId"));
            String animalSituation = request.queryParams("animalSituation");
            Sighting sighting = new Sighting(sightingId,animalId,rangerId,locationId,animalSituation);
            sighting.save();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Sighting sighting = Sighting.find(Integer.parseInt(request.params(":id")));
            sighting.delete();
            model.put("sightings", Sighting.all());
            return new ModelAndView(model,"sightings.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
