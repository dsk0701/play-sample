import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import play.*;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import tasks.GreetingActor;

import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {

    Cancellable cancellable;
    @Override
    public void onStart(Application app) {
        Logger.info("Application has started");

        ActorRef actor = Akka.system().actorOf(Props.create(GreetingActor.class));
        cancellable = Akka.system().scheduler().schedule(
                Duration.Zero(),
                Duration.create(5, TimeUnit.SECONDS),
                actor,
                GreetingActor.MESSAGE_GREETING,
                Akka.system().dispatcher(),
                null);

    }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");

        cancellable.cancel();
    }

}
