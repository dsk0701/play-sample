package controllers;

import com.google.inject.Inject;
import models.User;
import play.*;
import play.db.ebean.Transactional;
import play.mvc.*;

import services.GreetingService;
import views.html.*;

import java.io.IOException;

public class Application extends Controller {

    @Inject
    private GreetingService greetingService;

    public static Result index() {

        return ok(index.render("Your new application is ready."));
    }

    public Result greet() {

        String greeting = greetingService.greeting();
        return ok(index.render("Greet:" + greeting));
    }

    @Transactional
    public static Result func1() {

        // Ebean.beginTransaction();

        func2();

        User user = new User();
        user.email = "test@emal.com";
        user.name = "test.username";
        user.password = "test.password";
        user.save();

        // Ebean.endTransaction();

        return ok("Hello has().");
    }

    @Transactional
    public static void func2() {
        // Ebean.beginTransaction();

        User user = new User();
        user.email = "test2@emal.com";
        user.name = "test2.username";
        user.password = "test2.password";
        user.save();

        String str = null;
        str.isEmpty();

        User user1 = new User();
        user1.email = "test3@emal.com";
        user1.name = "test3.username";
        user1.password = "test3.password";
        user1.save();

        // Ebean.endTransaction();
    }

}
