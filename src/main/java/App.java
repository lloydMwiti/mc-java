import static spark.Spark.*;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class App {

    public static void main(String[] args) {

        staticFileLocation("/public");
         //the home page
        get("/home", (request, response) -> {
            return new ModelAndView(new HashMap(), "hello.hbs");
        }, new HandlebarsTemplateEngine());
        //the landing page
        get("/",(request ,response) ->
                "<img src='images/forest_fog_trees_128751_1920x1080.jpg'>"
                +"<a href='/home'>link to other page</a>"+"<br>"
                +"<a href='/form'>link to other page</a>"+"<br><br><br>");

        get("/form", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/greet", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String recipient = request.queryParams("recipient");
            String sender = request.queryParams("sender");
            model.put("recipient", recipient);
            model.put("sender", sender);
            return new ModelAndView(model, "greet.hbs");
        }, new HandlebarsTemplateEngine());
        
    }
}
