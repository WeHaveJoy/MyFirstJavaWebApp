//import jdk.internal.org.objectweb.asm.tree.InsnList;
import org.eclipse.jetty.server.Authentication;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static spark.Spark.*;

public class App {

  static   Map<String, Integer> count = new HashMap<String, Integer>();
  static   Map<String, Object> map = new HashMap<>();


    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {

        port(getHerokuAssignedPort());

    get("/greet", (req, res) -> "Hello ");

    get("/greet/:username", (req, res) -> {
            return "Hello: " + req.params(":username");
        });

    get("/greet/:username/language/:language", (req, res) -> {
        // Show something
       return "Hello";
    });

    get("greeted/:username", (req, res) -> {
           return "greeted: " + req.params(":username");

        });

       get("/greeted", (req, res) -> {

//            Map<String, Integer> items = new HashMap<>();
           ArrayList<String> arList = new ArrayList<String>();

           for(String names: count.keySet()){
               arList.add(names);

           }
           map.put("users", arList);

           System.out.println(arList);

            return new ModelAndView(map, "greeted.handlebars");


        }, new HandlebarsTemplateEngine());


    get("/hello", (req, res) -> {


            return new ModelAndView(map, "hello.handlebars");

        }, new HandlebarsTemplateEngine());



        post("/hello", (req, res) -> {

            Map<String, Object> map = new HashMap<>();


            // create the greeting message

            String message = "";

            String greeting =  req.queryParams("username");
            String language  =  req.queryParams("language");

//            int nameCount = count.size();
            if (!count.containsKey(greeting)) {
                count.put(greeting, 1);
            }
            else if(count.containsKey(greeting)) {
                count.put(greeting,  count.get(greeting) +1);
            }
         //   System.out.println(count);
            int nameCount = count.size();
            System.out.println(nameCount);


            if(language.equals("English")){
             message = "Hello, " + greeting;
            }
            else if(language.equals("IsiXhosa")){
              message = "Mholo, " + greeting;
            }
            else  if(language.equals("Afrikaans")){
                message = "Hallo, " + greeting;
            }
            else{
                System.out.println("Please choose a language that you would like to be greeted in.");
            }


            // put it in the map which is passed to the template - the value will be merged into the template
            map.put("greeting", message);
          map.put("counter", nameCount);

            return new ModelAndView(map, "hello.handlebars");

        }, new HandlebarsTemplateEngine());

    }
}