import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import play.*;
import play.libs.Akka;
import scala.concurrent.duration.Duration;
import services.GreetingService;
import services.RealGreetingService;
import tasks.GreetingActor;

import java.util.concurrent.TimeUnit;

public class Global extends GlobalSettings {

    private Injector injector;

    Cancellable cancellable;

    @Override
    public void onStart(Application app) {
        Logger.info("Application has started");

        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(GreetingService.class).to(RealGreetingService.class);
            }
        });

        startActor();
    }

    @Override
    public <T> T getControllerInstance(Class<T> aClass) throws Exception {
        return injector.getInstance(aClass);
    }

    @Override
    public void onStop(Application app) {
        Logger.info("Application shutdown...");

        stopActor();
    }

    private void startActor() {

        if (cancellable == null) {
            ActorRef actor = Akka.system().actorOf(Props.create(GreetingActor.class));
            cancellable = Akka.system().scheduler().schedule(
                    Duration.Zero(),
                    Duration.create(5, TimeUnit.SECONDS),
                    actor,
                    GreetingActor.MESSAGE_GREETING,
                    Akka.system().dispatcher(),
                    null);
        }
    }

    private void stopActor() {

        if (cancellable != null) {
            cancellable.cancel();
        }
    }

}
