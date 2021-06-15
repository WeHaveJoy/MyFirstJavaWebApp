import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
//       String language;

    get("/greet", (req, res) -> "Hello ");

    get("/greet/:username", (req, res) -> {
            return "Hello: " + req.params(":username");
        });

    get("/greet/:username/language/:language", (req, res) -> {
        // Show something
       return "Hello";
//        if(language == "English"){
//
//        }
    });

    get("greeted/:username", (req, res) -> {
           return "greeted: " + req.params(":username");

        });


    get("/hello", (req, res) -> {
         //   return "";

            Map<String, Object> map = new HashMap<>();
            return new ModelAndView(map, "hello.handlebars");

        }, new HandlebarsTemplateEngine());
       // });


        post("/hello", (req, res) -> {
            //return "";


            Map<String, Object> map = new HashMap<>();

            // create the greeting message

            String greeting = "Hello, " + req.queryParams("username");
            

//            if(language == "English"){
//             return "Hello, " + req.queryParams("username");
//            }
//            else  if(language == "IsiXhosa"){
//                return  "Mholo, " + req.queryParams("username");
//            }
//            else  if(language == "Afrikaans"){
//                return  "Hallo, " + req.queryParams("username");
//            }
//            else{
//                System.out.println("Please choose a language that you would like to be greeted in.");
//            }


            // put it in the map which is passed to the template - the value will be merged into the template
            map.put("greeting", greeting);

            return new ModelAndView(map, "hello.handlebars");

        }, new HandlebarsTemplateEngine());
       // });
    }
}